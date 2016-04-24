package com.widerwille.x86;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class x86SyntaxHighlighterFactory extends SyntaxHighlighterFactory
{
	@NotNull
	@Override
	public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile file)
	{
		return new x86SyntaxHighlighter();
	}
}
