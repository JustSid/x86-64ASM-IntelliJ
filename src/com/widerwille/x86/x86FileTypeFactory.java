package com.widerwille.x86;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

import org.jetbrains.annotations.NotNull;

public class x86FileTypeFactory extends FileTypeFactory
{
	@Override
	public void createFileTypes(@NotNull FileTypeConsumer consumer)
	{
		for(String extension : x86FileType.DEFAULT_ASSOCIATED_EXTENSIONS)
			consumer.consume(x86FileType.INSTANCE, extension);
	}
}
