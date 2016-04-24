package com.widerwille.x86;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import com.widerwille.x86.psi.*;

import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class x86SyntaxHighlighter extends SyntaxHighlighterBase
{
	public static final TextAttributesKey VALUE = createTextAttributesKey("x86_VALUE", DefaultLanguageHighlighterColors.CONSTANT);
	public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("x86_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

	public static final TextAttributesKey PREPROCESSOR = createTextAttributesKey("x86_PREPROCESSOR", DefaultLanguageHighlighterColors.METADATA);
	public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("x86_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey("x86_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

	public static final TextAttributesKey INSTRUCTION = createTextAttributesKey("x86_INSTRUCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL);
	public static final TextAttributesKey LABEL = createTextAttributesKey("x86_LABEL", DefaultLanguageHighlighterColors.LABEL);
	public static final TextAttributesKey REGISTER_GENERAL = createTextAttributesKey("x86_REGISTER_GENERAL", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey REGISTER_SPECIAL = createTextAttributesKey("x86_REGISTER_SPECIAL", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey REGISTER_SEGMENT = createTextAttributesKey("x86_REGISTER_SEGMENT", DefaultLanguageHighlighterColors.KEYWORD);

	private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
	private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
	private static final TextAttributesKey[] INSTRUCTION_KEYS = new TextAttributesKey[]{INSTRUCTION};
	private static final TextAttributesKey[] REGISTER_GENERAL_KEYS = new TextAttributesKey[]{REGISTER_GENERAL};
	private static final TextAttributesKey[] REGISTER_SPECIAL_KEYS = new TextAttributesKey[]{REGISTER_SPECIAL};
	private static final TextAttributesKey[] REGISTER_SEGMENT_KEYS = new TextAttributesKey[]{REGISTER_SEGMENT};
	private static final TextAttributesKey[] LABEL_KEYS = new TextAttributesKey[]{LABEL};
	private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
	private static final TextAttributesKey[] PREPROCESSOR_KEYS = new TextAttributesKey[]{PREPROCESSOR};
	private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
	private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

	@NotNull
	@Override
	public Lexer getHighlightingLexer()
	{
		return new x86LexerAdapter();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType)
	{
		if(tokenType.equals(x86Types.INSTRUCTION))
		{
			return INSTRUCTION_KEYS;
		}
		else if(tokenType.equals(x86Types.REGISTER_GENERAL))
		{
			return REGISTER_GENERAL_KEYS;
		}
		else if(tokenType.equals(x86Types.REGISTER_SPECIAL))
		{
			return REGISTER_SPECIAL_KEYS;
		}
		else if(tokenType.equals(x86Types.REGISTER_SEGMENT))
		{
			return REGISTER_SEGMENT_KEYS;
		}
		else if(tokenType.equals(x86Types.LABEL))
		{
			return LABEL_KEYS;
		}
		else if(tokenType.equals(x86Types.COMMENT_BLOCK))
		{
			return BLOCK_COMMENT_KEYS;
		}
		else if(tokenType.equals(x86Types.COMMENT_LINE))
		{
			return LINE_COMMENT_KEYS;
		}
		else if(tokenType.equals(x86Types.PREPROCESSOR))
		{
			return PREPROCESSOR_KEYS;
		}
		else if(tokenType.equals(x86Types.VALUE))
		{
			return VALUE_KEYS;
		}
		else if(tokenType.equals(TokenType.BAD_CHARACTER))
		{
			return BAD_CHAR_KEYS;
		}
		else
		{
			return EMPTY_KEYS;
		}
	}
}
