package com.widerwille.x86;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.widerwille.x86.psi.x86Types;

%%

%class x86Lexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

LINE_BREAK = \n|\r|\r\n
WHITE_SPACE = [\ \t\f]

REGISTERS_32BIT = EAX|EBX|ECX|EDX|ESI|EDI|EBP|EIP|ESP|eax|ebx|ecx|edx|esi|edi|ebp|eip|esp
REGISTERS_16BIT = AX|BX|CX|DX|SI|DI|BP|SP|IP|ax|bx|cx|dx|si|di|bp|sp|ip
REGISTERS_8BIT  = AH|AL|BH|BL|CH|CL|DH|DL|ah|al|bh|bl|ch|cl|dh|dl

SEGMENT_REGISTERS = CS|DS|ES|FS|GS|SS|cs|ds|es|fs|gs|ss
SPECIAL_REGISTERS = EFLAGS|eflags|CR0|CR1|CR2|CR3|CR4|cr0|cr1|cr2|cr3|cr4

INSTRUCTIONS = i:aa[adms]|adc[bwlq]?|x?add[bwlq]?|and[bwlq]?|arpl|bound[wl]?|bs[fr][wlq]?|bswap[lq]?|bt[crs]?[wlq]?|call[wlq]?|clc|cld|cli|cltd[dqw]?|clts|cmc|cmov(n?[abgl]e?|n?[ceosz]|np|p[eo]?)?[lqw]?|cmp[bwlq]?|cmps[bwdlq]?|cmpxchg[bwlq]?|cmpxchg(8|16)b|cpuid|c[lw]td[bwlq]?|daa|das|dec[bwlq]?|div[bwlq]?|enter[wl]?|esc|hlt|idiv[bwlq]?|imul[bwlq]?|in[bwlq]?|inc[bwlq]?|insd?[bwlq]?|int(\s*3|o)?|inv(d|pcid)?|invlpg|iret[dfwq]?|j(n?[abgl]e?|n?[ceosz]|np|p[eo]?)|jmp[lq]?|j[er]?cxz|[ls]ahf|lar|lcall[wlq]?|l[d-gs]s[wl]|lea[bwlq]?|leave[lwq]?|l[defgs]s|[ls][gil]dt[wl]?|ljmp[wl]?|lmsw|loadall|lods[bwdlq]?|loop(n?[ez][dw]?)?|loopw|lret|lsl|ltr|mov((s?(b[lwq]?|w[lq]?|lq?|q)?)|(z?(b[lwq]|w[lq]|l|q)))?|movd|movabs[bwlq]?A?|movs(x?d|w)|mov[sz]x[bwl]?|movzb|mul[bwlq]?|neg[bwlq]?|nop|not[bwlq]?|x?or[bwlq]?|out[bwlq]?|outs[bdwl]?|pop[bwlq]?|popal?|pop[af]d|popf[wlq]?|push[bwlq]?|pushal?|push[af]d|pushf[wlq]?|rc[lr][bwlq]?|(rd|wr)msr|rdtscp?|ret[fw]?[ql]?|ro[lr][bwlq]?|rsm|sa[lr][bwlq]?|sbb[bwlq]?|scas[bwdlq]?|set(n?[abgl]e?|n?[ceosz]|np|p[eo]?)b?|sh[lr]d?[bwlq]?|smsw|stc|std|sti|stos[bdqlw]?|str|sub[bwlq]?|swapgs|syscall|sysret|sysenter|sysexit|test[bwlq]?|ud1|ud2[ab]?|ver[rw]|fwait|wbinvd|xchg[bwlq]?A?|x[gs]etbv|xlatb?|xsave[cs]?(64)?|xrstors?(64)

NUMBER = (\-)?(0x)?[0-9A-Fa-f]*?
IDENTIFIER = [A-Za-z_]([A-Za-z0-9_])*?
LABEL = ([A-Za-z0-9])+?

IMMEDIATE_VALUE = \$({NUMBER}|{IDENTIFIER})

LINE_COMMENT        = "//"[^\r\n]*
BLOCK_COMMENT       = "/*"([^"*"]|("*"+[^"*""/"]))*("*"+"/")?

%state IN_INSTRUCTION
%state IN_PREPROCESSOR

%%

"#" { yybegin(IN_PREPROCESSOR); return x86Types.PREPROCESSOR; }

<IN_PREPROCESSOR>
{
	{WHITE_SPACE}+                 { return TokenType.WHITE_SPACE; }
	\\({WHITE_SPACE}?){LINE_BREAK} { return x86Types.PREPROCESSOR; }
	{LINE_BREAK}                   { yybegin(YYINITIAL); return x86Types.PREPROCESSOR; }
	\"([^\"\r\n])*\"               { return x86Types.PREPROCESSOR; }
	include                    { return x86Types.PREPROCESSOR; }
	define                { return x86Types.PREPROCESSOR; }
    undef                 { return x86Types.PREPROCESSOR; }
    if                    { return x86Types.PREPROCESSOR; }
    ifdef                 { return x86Types.PREPROCESSOR; }
    ifndef                { return x86Types.PREPROCESSOR; }
    else                  { return x86Types.PREPROCESSOR; }
    elif                  { return x86Types.PREPROCESSOR; }
    endif                 { return x86Types.PREPROCESSOR; }
    error                 { return x86Types.PREPROCESSOR; }
    pragma                { return x86Types.PREPROCESSOR; }
    extension             { return x86Types.PREPROCESSOR; }
    version               { return x86Types.PREPROCESSOR; }
    line                  { return x86Types.PREPROCESSOR; }
    defined               { return x86Types.PREPROCESSOR; }
    ##                    { return x86Types.PREPROCESSOR; }
}

<YYINITIAL>
{
	({WHITE_SPACE}|{LINE_BREAK})+ { return TokenType.WHITE_SPACE; }
}

\%({REGISTERS_32BIT}|{REGISTERS_16BIT}|{REGISTERS_8BIT}) { return x86Types.REGISTER_GENERAL; }
\%{SEGMENT_REGISTERS} { return x86Types.REGISTER_SEGMENT; }
\%{SPECIAL_REGISTERS} { return x86Types.REGISTER_SPECIAL; }

{INSTRUCTIONS} { return x86Types.INSTRUCTION; }

"("  { return x86Types.LEFT_PAREN; }
")"  { return x86Types.RIGHT_PAREN; }
"/"  { return x86Types.SLASH; }
"\\" { return x86Types.BACKSLASH; }

"." { return x86Types.DOT; }
"*" { return x86Types.STAR; }
":" { return x86Types.COLON; }
";" { return x86Types.SEMICOLON; }
"," { return x86Types.COMMA; }
"<" { return x86Types.LEFT_ANGLE; }
">" { return x86Types.RIGHT_ANGLE; }


(\$)?{IDENTIFIER} { return x86Types.IDENTIFIER; }
(\$)?{NUMBER} { return x86Types.VALUE; }
{LABEL}\: { return x86Types.LABEL; }

{LINE_COMMENT} { return x86Types.COMMENT_LINE; }
{BLOCK_COMMENT} { return x86Types.COMMENT_BLOCK; }

. { return TokenType.BAD_CHARACTER; }

