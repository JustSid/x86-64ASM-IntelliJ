package com.widerwille.x86;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class x86ColorSettingsPage implements ColorSettingsPage
{
	private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
			new AttributesDescriptor("General Purpose Register", x86SyntaxHighlighter.REGISTER_GENERAL),
			new AttributesDescriptor("Segment Register", x86SyntaxHighlighter.REGISTER_SEGMENT),
			new AttributesDescriptor("Special Register", x86SyntaxHighlighter.REGISTER_SPECIAL),
			new AttributesDescriptor("Preprocessor", x86SyntaxHighlighter.PREPROCESSOR),
			new AttributesDescriptor("Line Comment", x86SyntaxHighlighter.LINE_COMMENT),
			new AttributesDescriptor("Block Comment", x86SyntaxHighlighter.BLOCK_COMMENT),
			new AttributesDescriptor("Label", x86SyntaxHighlighter.LABEL),
			new AttributesDescriptor("Instruction", x86SyntaxHighlighter.INSTRUCTION),
			new AttributesDescriptor("Value", x86SyntaxHighlighter.VALUE),
	};

	@Nullable
	@Override
	public Icon getIcon()
	{
		return x86Icons.FILE;
	}

	@NotNull
	@Override
	public SyntaxHighlighter getHighlighter()
	{
		return new x86SyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText()
	{
		return "#include \"../asm.h\"\n" +
			"\n" +
			".text\n" +
			"\n" +
			"ENTRY(__syscall)\n" +
			"	pushl %ebp\n" +
			"	movl %esp, %ebp\n" +
			"\n" +
			"	pushl %edi\n" +
			"	pushl %esi\n" +
			"	pushl %ebx\n" +
			"\n" +
			"	movl 0x8(%ebp), %eax\n" +
			"\n" +
			"	movl 0xc(%ebp), %ecx\n" +
			"	movl 0x10(%ebp), %edi\n" +
			"	movl 0x14(%ebp), %esi\n" +
			"	movl 0x18(%ebp), %edx\n" +
			"	movl 0x1c(%ebp), %ebx\n" +
			"\n" +
			"	int  $0x80\n" +
			"\n" +
			"	jecxz 1f\n" +
			"\n" +
			"	pushl %eax\n" +
			"	pushl %ecx\n" +
			"	call __tls_setErrno\n" +
			"	add $0x8, %esp\n" +
			"\n" +
			"1:\n" +
			"	popl %ebx\n" +
			"	popl %esi\n" +
			"	popl %edi\n" +
			"\n" +
			"	popl %ebp\n" +
			"	ret\n" +
			"\n";
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
	{
		return null;
	}

	@NotNull
	@Override
	public AttributesDescriptor[] getAttributeDescriptors()
	{
		return DESCRIPTORS;
	}

	@NotNull
	@Override
	public ColorDescriptor[] getColorDescriptors()
	{
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public String getDisplayName()
	{
		return "x86-64 Assembler";
	}
}
