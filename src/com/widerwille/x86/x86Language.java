package com.widerwille.x86;

import com.intellij.lang.Language;

public class x86Language extends Language
{
	public static final x86Language INSTANCE = new x86Language();

	private x86Language()
	{
		super("x86-64");
	}
}
