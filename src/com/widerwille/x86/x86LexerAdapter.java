package com.widerwille.x86;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class x86LexerAdapter extends FlexAdapter
{
	public x86LexerAdapter()
	{
		super(new x86Lexer(null));
	}
}
