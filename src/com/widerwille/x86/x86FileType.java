package com.widerwille.x86;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class x86FileType extends LanguageFileType
{
	public static final x86FileType INSTANCE = new x86FileType();
	public static final String[] DEFAULT_ASSOCIATED_EXTENSIONS = { "S" };

	private x86FileType()
	{
		super(x86Language.INSTANCE);
	}

	@NotNull
	@Override
	public String getName()
	{
		return "x86-64 Assembler";
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return "x86-64 Assembler file";
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "S";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return x86Icons.FILE;
	}
}
