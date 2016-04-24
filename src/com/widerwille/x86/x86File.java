package com.widerwille.x86;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class x86File extends PsiFileBase
{
	public x86File(@NotNull FileViewProvider viewProvider)
	{
		super(viewProvider, x86Language.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return x86FileType.INSTANCE;
	}

	@Override
	public String toString()
	{
		return "x86 Assembler File";
	}

	@Override
	public Icon getIcon(int flags)
	{
		return super.getIcon(flags);
	}
}
