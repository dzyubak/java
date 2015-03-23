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

/**
 * @author Dmytro Dzyubak
 *
 */
public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			double number;
			number = StringToDoubleConverter.convertStringToDouble("Hello3.14E+09World");
			System.out.println(number);
			number = StringToDoubleConverter.convertStringToDouble("Hello-2718.28e-09World");
			System.out.println(number);
			number = StringToDoubleConverter.convertStringToDouble("HelloW-42orld");
			System.out.println(number);
			number = StringToDoubleConverter.convertStringToDouble("Hello00000World");
			System.out.println(number);
			number = StringToDoubleConverter.convertStringToDouble("He.llo+World");
			System.out.println(number);
		} catch (ParserException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}