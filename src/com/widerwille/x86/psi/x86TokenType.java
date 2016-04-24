package com.widerwille.x86.psi;

import com.intellij.psi.tree.IElementType;
import com.widerwille.x86.x86Language;
import org.jetbrains.annotations.*;

public class x86TokenType extends IElementType
{
	public x86TokenType(@NotNull @NonNls String debugName)
	{
		super(debugName, x86Language.INSTANCE);
	}

	@Override
	public String toString()
	{
		return "x86TokenType." + super.toString();
	}
}