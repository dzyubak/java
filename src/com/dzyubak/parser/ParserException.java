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
public class ParserException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ParserException(String m) {
		message = m;
	}
	
	public String toString() {
		return "Error! ParserException[" + message + "]";
	}
	
}