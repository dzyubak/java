/**
 * Copyright (C) 2014, 2015 Dmytro Dzyubak
 * 
 * This file is part of com.dzyubak.parser.
 * 
 * com.dzyubak.parser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * com.dzyubak.parser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with com.dzyubak.parser. If not, see <http://www.gnu.org/licenses/>.
 */

package com.dzyubak.parser;

interface States { // constants
	int NAN   = 0; // Not a number
	int DIGIT = 1; // 1..9
	int ZERO  = 2; // 0
	int POINT = 3; // "."
	int E     = 4; // e, E
	int SIGN  = 5; // minus "-", plus "+"
}

interface Actions {
	int SKIP   = 0;
	int APPEND = 1;
	int CLEAR  = 2;
	int STOP   = 3;
}

/**
 * @author Dmytro Dzyubak
 *
 */
public class StringToDoubleConverter implements States, Actions {
	
	public static double convertStringToDouble(String str) throws ParserException {
		char[] chArr = str.toCharArray();
		int state     = NAN;
		int prevState = NAN; // previous state
		int action    = SKIP;
		
		char[] number = new char[chArr.length];
		int idx = 0;
		boolean pointIsSet = false; // point can be set ONLY once
		boolean eIsSet     = false; // "E" can be set ONLY once
		
		String numberStr;
		
		for(int i = 0; i < chArr.length; i++) {
			state = charToState(chArr[i]);
			action = analyseState(state, prevState, pointIsSet, eIsSet);
			if(state == POINT) {
				pointIsSet = true;
			}
			if(state == E) {
				eIsSet = true;
			}
			
			if(action == APPEND) {
				number[idx] = chArr[i];
				idx++;
			} else if(action == CLEAR) {
				number = new char[chArr.length];
				idx    = 0;
				pointIsSet = false;
				eIsSet     = false;
			} else if(action == STOP) {
				break;
			} // else - SKIP
			prevState = state;
		}
		if(idx == 0) {
			throw new ParserException("Number can not be found in the current string.");
		}
		numberStr = new String(number, 0, idx);
		//System.out.println("String: " + numberStr);
		return Double.valueOf(numberStr); // Double.valueOf(numberStr).longValue();
	}
	
	public static int charToState(char ch) {
		if('1' <= ch & ch <= '9') {
			return DIGIT;
		} else if(ch == '0') {
			return ZERO;
		} else if(ch == '.') {
			return POINT;
		} else if(ch == 'e' | ch == 'E') {
			return E;
		} else if(ch == '-' | ch == '+') {
			return SIGN;
		} else {
			return NAN;
		}
	}
	
	public static int analyseState(int state, int prevState,
								   boolean pointIsSet, boolean eIsSet) throws ParserException {
		switch(state) {
		case DIGIT:
			return APPEND;
		case ZERO:
			//if(prevState == ZERO) {
			//	return CLEAR;
			//}
			return APPEND;
		case POINT:
			if(prevState == NAN) {
				return SKIP;
			} else if(pointIsSet) {
				return STOP;
			} else {
				return APPEND;
			}
		case E:
			if(eIsSet) {
				return CLEAR;
			} else if(prevState == DIGIT | prevState == ZERO) {
				return APPEND;
			} else if(prevState == NAN) {
				return SKIP;
			} else { // after SIGN, POINT
				return CLEAR;
			}
		case SIGN:
			if(prevState == NAN | prevState == E) {
				return APPEND;
			} else { // POINT, SIGN, DIGIT, ZERO
				return CLEAR;
			}
		case NAN:
			if(prevState == NAN) {
				return SKIP;
			} else if(prevState == DIGIT | prevState == ZERO) {
				return STOP;
			} else {
				return CLEAR;
			}
		default:
			throw new ParserException("Invalid case.");
		}
	}
	
}