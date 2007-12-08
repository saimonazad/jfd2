/*
 * 2004/10/22 getAnchor, getStripHTMLTag�̃R�[�h���œK��.
 * 2004/09/24 getString���\�b�h�ǉ�.
 * 2004/09/09 getReplaceHTMLEntity���\�b�h��ǉ�.
 * 2004/09/07 convertTagSpace���\�b�h��ǉ�.
 * 2004/09/06 getAnchor(str, title, cut)���\�b�h��, �啶��URL�����߂���Ȃ��s��C��.
 * 2004/03/05 getAnchor���\�b�h�Ń^�C�g���^�O���s���ɂȂ�s��C��.
 * 2004/02/20 getLinkAnchor���\�b�h�̐��K�\����ORO�p�b�P�[�W�ɕύX. ������J�b�g�@�\�ǉ�.
 * 2004/02/20 �V�K�쐬. �ꕔ���\�b�h��StringUtil�N���X���ړ�.
 */

package org.dyndns.longinus.utils;

import org.apache.oro.text.perl.Perl5Util;
import org.apache.oro.text.regex.PatternMatcherInput;

/**
 * HTML��\�������ŗ��p�ł��郆�[�e�B���e�B�N���X.
 */
public class HTMLUtil extends StringUtil {
    public static final int TAG_TO_SPACE = 1;
    public static final int SPACE_TO_TAG = 0;
    public static final int CONV_HTML_ENTITY= 0;
    public static final int CONV_ESCAPE_ENTITY = 1;

    public static final String REGEXP_VALID_URL = "s?https?:\\/\\/[-_.!~*'()a-zA-Z0-9;\\/?:@&=+$,%#]+";
    public static final String REGEXP_VALID_MAIL = "([a-z0-9_]|\\-|\\.)+@(([a-z0-9_]|\\-)+\\.)+[a-z]{2,4}";

    private static final String REGEXP_STRIP_TEXT = "[^<]*";
    private static final String REGEXP_STRIP_TAG_ = "[^\"'<>]*(?:\"[^\"]*\"[^\"'<>]*|'[^']*'[^\"'<>]*)*(?:>|(?=<)|$(?!\n))";
    private static final String REGEXP_STRIP_COMMENT = "<!(?:--[^-]*-(?:[^-]+-)*?-(?:[^>-]*(?:-[^>-]+)*?)??)*(?:>|$(?!\n)|--.*$)";
    private static final String REGEXP_STRIP_TAG = REGEXP_STRIP_COMMENT + "|<" + REGEXP_STRIP_TAG_;
    private static final String REGEXP_CRLF_SET = "\r\n|\n|\r";
    private static final char CHAR_SPACE = ' ';
    private static final char CHAR_TAB = '\t';

    private static final String[][] MINIMUM_SPECIAL_CHARACTERS =
        {
            {"&quot;", "\""}, {"&amp;", "&"}, {"&lt;", "<"}, {"&gt;", ">"}, {"&nbsp;", " "}
        };
    private static final String[][] SPECIAL_CHARACTERS =
        {
            {"&quot;", "\""}, {"&amp;", "&"}, {"&lt;", "<"}, {"&gt;", ">"}, {"&nbsp;", " "}, {"&iexcl;", "!"},
            {"&cent;", "��"}, {"&pound;", "��"}, {"&curren;", "?"}, {"&yen;", "\\"}, {"&brvbar;", "|"}, {"&sect;", "��"},
            {"&uml;", "�N"}, {"&copy;", "c"}, {"&ordf;", "a"}, {"&laquo;", "��"}, {"&not;", "��"}, {"&shy;", "-"},
            {"&reg;", "R"}, {"&macr;", "�P"}, {"&deg;", "��"}, {"&plusmn;", "�}"}, {"&sup2;", "2"},
            {"&sup3;", "3"},{"&acute;", "�L"}, {"&micro;", "��"}, {"&para;", "��"}, {"&middot;", "�E"}, {"&cedil;", "�C"},
            {"&sup1;", "1"}, {"&ordm;", "o"}, {"&raquo;", "��"}, {"&frac14;", "?"}, {"&frac12;", "?"}, {"&frac34;", "?"},
            {"&iquest;", "?"}, {"&Agrave;", "A"}, {"&Aacute;", "A"}, {"&Acirc;", "A"}, {"&Atilde;", "A"}, {"&Auml;", "A"},
            {"&Aring;", "A"}, {"&AElig;", "A"}, {"&Ccedil;", "C"}, {"&Egrave;", "E"}, {"&Eacute;", "E"}, {"&Ecirc;", "E"},
            {"&Euml;", "E"}, {"&Igrave;", "I"}, {"&Iacute;", "I"}, {"&Icirc;", "I"}, {"&Iuml;", "I"}, {"&ETH;", "D"},
            {"&Ntilde;", "N"}, {"&Ograve;", "O"}, {"&Oacute;", "O"}, {"&Ocirc;", "O"}, {"&Otilde;", "O"}, {"&Ouml;", "O"},
            {"&times;", "�~"}, {"&Oslash;", "O"}, {"&Ugrave;", "U"}, {"&Uacute;", "U"}, {"&Ucirc;", "U"}, {"&Uuml;", "U"},
            {"&Yacute;", "Y"}, {"&THORN;", "T"}, {"&szlig;", "s"}, {"&agrave;", "a"}, {"&aacute;", "a"}, {"&acirc;", "a"},
            {"&atilde;", "a"}, {"&auml;", "a"}, {"&aring;", "a"}, {"&aelig;", "a"}, {"&ccedil;", "c"}, {"&egrave;", "e"},
            {"&eacute;", "e"}, {"&ecirc;", "e"}, {"&euml;", "e"}, {"&igrave;", "i"}, {"&iacute;", "i"}, {"&icirc;", "i"},
            {"&iuml;", "i"}, {"&eth;", "d"}, {"&ntilde;", "n"}, {"&ograve;", "o"}, {"&oacute;", "o"}, {"&ocirc;", "o"},
            {"&otilde;", "o"}, {"&ouml;", "o"}, {"&divide;", "��"}, {"&oslash;", "o"}, {"&ugrave;", "u"}, {"&uacute;", "u"},
            {"&ucirc;", "u"}, {"&uuml;", "u"}, {"&yacute;", "y"}, {"&thorn;", "t"}, {"&yuml;", "y"}, {"&OElig;", "?"},
            {"&oelig;", "?"}, {"&Scaron;", "?"}, {"&scaron;", "?"}, {"&Yuml;", "?"}, {"&fnof;", "?"}, {"&circ;", "?"},
            {"&tilde;", "?"}, {"&Alpha;", "��"}, {"&Beta;", "��"}, {"&Gamma;", "��"}, {"&Delta;", "��"}, {"&Epsilon;", "��"},
            {"&Zeta;", "��"}, {"&Eta;", "��"}, {"&Theta;", "��"}, {"&Iota;", "��"}, {"&Kappa;", "��"}, {"&Lambda;", "��"},
            {"&Mu;", "��"}, {"&Nu;", "��"}, {"&#xi;", "��"}, {"&Omicron;", "��"}, {"&Pi;", "��"}, {"&Rho;", "��"},
            {"&Sigma;", "��"}, {"&Tau;", "��"}, {"&Upsilon;", "��"}, {"&Phi;", "��"}, {"&Chi;", "��"}, {"&Psi;", "��"},
            {"&Omega;", "��"}, {"&alpha;", "��"}, {"&beta;", "��"}, {"&gamma;", "��"}, {"&delta;", "��"}, {"&epsilon;", "��"},
            {"&zeta;", "��"}, {"&eta;", "��"}, {"&theta;", "��"}, {"&iota;", "��"}, {"&kappa;", "��"}, {"&lambda;", "��"},
            {"&mu;", "��"}, {"&nu;", "��"}, {"&#xi;", "��"}, {"&omicron;", "��"}, {"&pi;", "��"}, {"&rho;", "��"},
            {"&sigmaf;", "?"}, {"&sigma;", "��"}, {"&tau;", "��"}, {"&upsilon;", "��"}, {"&phi;", "��"}, {"&chi;", "��"},
            {"&psi;", "��"}, {"&omega;", "��"}, {"&thetasym;", "?"}, {"&upsih;", "?"}, {"&piv;", "?"}, {"&bull;", "?"},
            {"&hellip;", "�c"}, {"&prime;", "��"}, {"&Prime;", "��"}, {"&oline;", "?"}, {"&frasl;", "?"}, {"&weierp;", "?"},
            {"&image;", "?"}, {"&real;", "?"}, {"&trade;", "?"}, {"&alefsym;", "?"}, {"&larr;", "��"}, {"&uarr;", "��"},
            {"&rarr;", "��"}, {"&darr;", "��"}, {"&harr;", "?"}, {"&crarr;", "?"}, {"&lArr;", "?"}, {"&uArr;", "?"},
            {"&rArr;", "��"}, {"&dArr;", "?"}, {"&hArr;", "��"}, {"&forall;", "��"}, {"&part;", "��"}, {"&exist;", "��"},
            {"&empty;", "?"}, {"&nabla;", "��"}, {"&isin;", "��"}, {"&notin;", "?"}, {"&ni;", "��"}, {"&prod;", "?"},
            {"&sum;", "��"}, {"&minus;", "?"}, {"&lowast;", "?"}, {"&radic;", "��"}, {"&prop;", "��"}, {"&infin;", "��"},
            {"&ang;", "��"}, {"&and;", "��"}, {"&or;", "��"}, {"&cap;", "��"}, {"&cup;", "��"}, {"&int;", "��"},
            {"&there4;", "��"}, {"&sim;", "?"}, {"&cong;", "?"}, {"&asymp;", "?"}, {"&ne;", "��"}, {"&equiv;", "��"},
            {"&le;", "?"}, {"&ge;", "?"}, {"&sub;", "��"}, {"&sup;", "��"}, {"&nsub;", "?"}, {"&sube;", "��"},
            {"&supe;", "��"}, {"&oplus;", "?"}, {"&otimes;", "?"}, {"&perp;", "��"}, {"&sdot;", "?"}, {"&lceil;", "?"},
            {"&rceil;", "?"}, {"&lfloor;", "?"}, {"&rfloor;", "?"}, {"&lang;", "?"}, {"&rang;", "?"}, {"&loz;", "?"},
            {"&spades;", "?"}, {"&clubs;", "?"}, {"&hearts;", "?"}, {"&diams;", "?"}, {"&ensp;", "?"}, {"&emsp;", "?"},
            {"&thinsp;", "?"}, {"&zwnj;", "?"}, {"&zwj;", "?"}, {"&lrm;", "?"}, {"&rlm;", "?"}, {"&ndash;", "?"},
            {"&mdash;", "?"}, {"&lsquo;", "�e"}, {"&rsquo;", "�f"}, {"&sbquo;", "?"}, {"&ldquo;", "�g"}, {"&rdquo;", "�h"},
            {"&bdquo;", "?"}, {"&dagger;", "��"}, {"&Dagger;", "��"}, {"&permil;", "��"}, {"&lsaquo;", "?"}, {"&rsaquo;", "?"},
            {"&euro;", "?"}
        };

    /**
     * �󕶎�(���邢��null)��HTML�̋󔒕����ɒu��������.
     *
     * @param str �ΏۂƂȂ镶����.
     * @return �Ώە����񂪋󕶎�, ���邢��null�ł������ꍇ, HTML�G���e�B�e�B�́u&amp;nbsp;�v��Ԃ�.
     */
    public static String getString(String str) {
        if (isString(str)) {
            return str;
        }

        return "&nbsp;";
    }

    /**
     * �Ώە�������̃^�O�ƃX�y�[�X(����)�̒u�����s��.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param conv �u������^�C�v. TAG_TO_SPACE, SPACE_TO_TAG�̂����ꂩ���w��.
     * @param size �ϊ�����L�����T�C�Y���w�肷��.
     * @return �^�O�ƃX�y�[�X���u�����ꂽ�������Ԃ�.
     */
    public static String convertTagSpace(String str, int conv, int size) {
        StringBuffer sb = new StringBuffer(size);
        String from = null;
        String to = null;

        if (conv == TAG_TO_SPACE) {
            for (int i = 0; i < size; i++) {
                sb.append(CHAR_SPACE);
            }

            from = Character.toString(CHAR_TAB);
            to = sb.toString();

        } else {
            for (int i = 0; i < size; i++) {
                sb.append(CHAR_TAB);
            }

            from = sb.toString();
            to = Character.toString(CHAR_TAB);
        }

        return replaceAll(str, from, to);
    }

    /**
     * �Ώە�������̃^�O�ƃX�y�[�X(����)�̒u�����s��.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param convert �u������^�C�v. TAG_TO_SPACE, SPACE_TO_TAG�̂����ꂩ���w��.
     * @return �^�O�ƃX�y�[�X���u�����ꂽ�������Ԃ�.
     */
    public static String convertTagSpace(String str, int convert) {
        return convertTagSpace(str, convert, 2);
    }

    /**
     * �v���[���ȕ�����ɖ��ߍ��܂�Ă���URI��, HTML�^�O�̃A���J�[��t�^����.
     *
     * @param str �ΏۂƂȂ�v���[��������.
     * @param title href��title������URI��ݒ肷��ꍇ��true.
     * @param cut ��ʏ�ŃA���J�[�������\������o�C�g�����w��.
     * @return �A���J�[�������Ԃ�.
     */
    public static String getAnchor(String str) {
        return getAnchor(str, false, -1);
    }

    /**
     * �v���[���ȕ�����ɖ��ߍ��܂�Ă���URI��, HTML�^�O�̃A���J�[��t�^����.
     *
     * @param str �ΏۂƂȂ�v���[��������.
     * @param title href��title������URI��ݒ肷��ꍇ��true.
     * @param cut ��ʏ�ŃA���J�[�������\������o�C�g�����w��.
     * @return �A���J�[�������Ԃ�.
     */
	public static String getAnchor(String str, boolean title, int cut) {
		if (isString(str)) {
			Perl5Util perl = new Perl5Util();
			String ret;
			
			// �����N������̐؂��薳��(�P���u��).
			if (cut == -1) {
				StringBuffer sb;

				// URL�̃}�b�`���O.
				sb = new StringBuffer();
				sb.append("s/(");
				sb.append(REGEXP_VALID_URL);
				sb.append(")/<a href=\"$1\"");

				if (title) {
					sb.append(" title=\"$1\"");
				}
	
				sb.append(">$1<\\/a>/gi");
				str = perl.substitute(sb.toString(), str);

				// ���[���A�h���X�̃}�b�`���O.
				sb = new StringBuffer();
				sb.append("s/(");
				sb.append(REGEXP_VALID_MAIL);
				sb.append(")/<a href=\"mailto:$1\"");

				if (title) {
					sb.append(" title=\"$1\"");
				}

				sb.append(">$1<\\/a>/gi");
				ret = perl.substitute(sb.toString(), str);

			// �����N������̐؂���L��.
			} else {
				// URL�̃}�b�`���O
				PatternMatcherInput pmi = new PatternMatcherInput(str);
				StringBuffer sb = new StringBuffer();
				sb.append("/(");
				sb.append(REGEXP_VALID_URL);
				sb.append(")/i");

				String pattern = sb.toString();
				sb = new StringBuffer();
				int offset = 0;
				
				while (perl.match(pattern, pmi)) {
					sb.append(perl.preMatch().substring(offset));
					sb.append("<a href=\"");
					sb.append(perl.group(1));
					sb.append("\"");

					if (title) {
					    sb.append(" title=\"");
					    sb.append(perl.group(1));
					    sb.append("\"");
					}

					sb.append(">");
					sb.append(getCutString(perl.group(1), cut));
					sb.append("</a>");

					offset = perl.endOffset(0);
				}
	
				if (offset > 0) {
				    sb.append(perl.postMatch());
					ret = sb.toString();
				} else {
					ret = str;
				}

				// ���[���A�h���X�̃}�b�`���O.
				pmi = new PatternMatcherInput(ret);
				sb = new StringBuffer();
				sb.append("/(");
				sb.append(REGEXP_VALID_MAIL);
				sb.append(")/");

				pattern = sb.toString();
				sb = new StringBuffer();
				offset = 0;

				while (perl.match(pattern, pmi)) {
				    sb.append(perl.preMatch().substring(offset));
				    sb.append("<a href=\"mailto:");
				    sb.append(perl.group(1));
				    sb.append("\"");

					if (title) {
					    sb.append(" title=\"");
					    sb.append(perl.group(1));
					    sb.append("\"");
					}

					sb.append(">");
					sb.append(getCutString(perl.group(1), cut));
					sb.append("</a>");
					offset = perl.endOffset(0);
				}

				if (offset > 0) {
				    sb.append(perl.postMatch());
					ret = sb.toString();
				}
				
			}

			return ret;
		}
		
		return str;
	}
    /**
     * ���s�R�[�h��HTML�̉��s�^�O�ɒu������.
     *
     * @param str �ΏۂƂȂ镶����.
     * @return ���s�R�[�h��HTML��BR�^�O�ɒu�������������Ԃ�.
     */
    public static String getLineFeed(String str) {
        return str.replaceAll(REGEXP_CRLF_SET, "<br>");
    }

    /**
     * HTML�G���e�B�e�B���G�X�P�[�v����.
     *
     * @param str �ΏۂƂȂ镶����.
     * @return HTML�G���e�B�e�B���G�X�P�[�v�����������Ԃ�.
     */
    public static String getHTMLEscape(String str) {
        if (isString(str)) {
            StringBuffer buf = new StringBuffer();
            String entity = null;

            char[] c = str.toCharArray();
            int i, last;
            int j = c.length;

            for (i = 0, last = 0; i < j; i++) {
                switch (c[i]) {
                    case '<':
                        entity = "&lt;";
                        break;
                    case '>':
                        entity = "&gt;";
                        break;
                    case '&':
                        entity = "&amp;";
                        break;
                    case '"':
                        entity = "&quot;";
                        break;
                }

                if (entity != null) {
                    buf.append(c, last, i - last);
                    buf.append(entity);

                    entity = null;
                    last = i + 1;
                }
            }

            if (last < j) {
                buf.append(c, last, i - last);
            }

            return buf.toString();

        } else {
            return str;
        }
    }

    /**
     * �w�肳�ꂽ�����񂩂�HTML�^�O����菜��.
     * (���K�\���p�^�[���ɂ��Ă�http://www.din.or.jp/~ohzaki/regex.htm��Perl�\�[�X����ڐA)
     *
     * @param input HTML�^�O���܂ޕ�����
     * @return �^�O����������������
     */
    public static String getStripHTMLTag(String str) {
        if (isString(str)) {
            Perl5Util perl = new Perl5Util();
            PatternMatcherInput pmi = new PatternMatcherInput(str);

            StringBuffer sb = new StringBuffer();
            sb.append("m/(");
            sb.append(REGEXP_STRIP_TEXT);
            sb.append(")(");
            sb.append(REGEXP_STRIP_TAG);
            sb.append(")?/s");

            String[] group = new String[2];
            String pattern = sb.toString();
            sb = new StringBuffer();

            while(perl.match(pattern, pmi)){
                group[0] = perl.group(1);
                group[1] = perl.group(2);

                if (group[0] == null) {
                    group[0] = "";
                }

                if (group[1] == null) {
                    group[1] = "";
                }

                sb.append(group[0]);
                PatternMatcherInput pPmi = new PatternMatcherInput(group[1]);

                if (perl.match("/^<(XMP|PLAINTEXT|SCRIPT)(?![0-9A-Za-z])/i", pPmi)) {
                    StringBuffer buf = new StringBuffer();
                    buf.append("/(.*?)(?:</?");
                    buf.append(perl.group(1));
                    buf.append("(?![0-9A-Za-z])");
                    buf.append(REGEXP_STRIP_TAG);
                    buf.append("|$)/si");

                    perl.match(pattern.toString(), pmi);
                    sb.append(perl.group(1));
                }
            }

            return getReplaceHTMLEntity(sb.toString(), CONV_ESCAPE_ENTITY, false);
        }

        return str;
    }

    /**
     * HTML�̓��ꕶ�����G�X�P�[�v�����ɕϊ�, �܂��͂��̋t���s��.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param type �ϊ�����^�C�v. CONVERT_HTML_ENTITY, CONVERT_ESCAPE_ENTITY�̂����ꂩ���w��.
     * @param scan �S�Ă̓��ꕶ����ϊ�����ꍇ��true, �ŏ����̕����݂̂�ϊ�����ꍇ��false���w��.
     * @return HTML�̓��ꕶ����ϊ��������ʕ������Ԃ�.
     */
    public static String getReplaceHTMLEntity(String str, int type, boolean scan) {
        if (isString(str)) {
            String[][] array;
            int from, to;

            if (type == CONV_HTML_ENTITY) {
                from = 1;
                to = 0;
            } else {
                from = 0;
                to = 1;
            }

            if (scan) {
                array = SPECIAL_CHARACTERS;
            } else {
                array = MINIMUM_SPECIAL_CHARACTERS;
            }

            int len = array.length;
            for (int i = 0; i < len; i++) {
                str = replaceAll(str, array[i][from], array[i][to]);
            }
        }

        return str;
    }
}
