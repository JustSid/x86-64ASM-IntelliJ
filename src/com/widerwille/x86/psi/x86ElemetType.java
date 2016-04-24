package com.widerwille.x86.psi;

import com.intellij.psi.tree.IElementType;
import com.widerwille.x86.x86Language;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class x86ElemetType extends IElementType
{
	public x86ElemetType(@NotNull @NonNls String debugName)
	{
		super(debugName, x86Language.INSTANCE);
	}
}
