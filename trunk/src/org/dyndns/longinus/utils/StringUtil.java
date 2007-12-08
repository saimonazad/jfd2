/*
 * 2004/08/19 getMaskFilterString���\�b�h��ǉ�.
 * 2004/05/26 trim���\�b�h��ǉ�.
 * 2004/05/26 toConvertString���\�b�h��"��"�����ݕϊ�����Ȃ��s��C��.
 * 2004/05/26 toConvertString���\�b�h�őS�p�p�����̈ꕔ�����p�ɕϊ�����Ȃ��s��C��.
 * 2004/04/28 getNumberFormat���\�b�h�ǉ�.
 * 2004/03/31 toConvertString, ���{��L���̕ϊ��ɑΉ�.
 * 2004/03/30 getReplaceString���\�b�h��null���n���ꂽ�ꍇ�̕s��Ή�.
 * 2004/03/12 getReplaceString�ǉ�
 * 2004/03/05 getCutString���\�b�h�ň���cut��3�ȉ����w�肳�ꂽ�ꍇ�̕s��C��.
 * 2004/02/20 getStripHTMLTag, getSQLString���\�b�h�̃p�t�H�[�}���X���P.
 * 2004/02/07 getStripHTMLTag���\�b�h�ŕ����񂩂�n�܂�HTML��u���ł��Ȃ��s��C��.
 * 2004/02/05 �V�K�쐬.
 */

package org.dyndns.longinus.utils;

import java.text.NumberFormat;

/**
 * ������𑀍삷�郆�[�e�B���e�B�N���X.
 */
public class StringUtil {
    /** �p���L��������S�p�����ɒu��.*/
    public static final int CONV_AL_FULL_WIDTH = 1;

    /** �p���L�������𔼊p�����ɒu��.*/
    public static final int CONV_AL_HALF_WIDTH = 2;

    /** ���p�J�i��S�p�J�i�ɒu��.*/
    public static final int CONV_JP_FULL_WIDTH = 4;

    /** �S�p�J�i�𔼊p�J�i�ɒu��.*/
    public static final int CONV_JP_HALF_WIDTH = 8;

    /** ��������Љ����ɒu��. */
    public static final int CONV_JP_KATAKANA = 16;

    /** �Љ����𕽉����ɒu��. */
    public static final int CONV_JP_HIRAGANA = 32;

    /** ���{�ꔼ�p�L����S�p�L���ɒu��. */
    public static final int CONV_JP_CHARS_FULL_WIDTH = 64;

    /** ���{��S�p�L���𔼊p�L���ɒu��. */
    public static final int CONV_JP_CHARS_HALF_WIDTH = 128;

    private static final char[][] JP_CHAR_TBL = {
        {0xff61, 0x3002}, {0xff62, 0x300c}, {0xff63, 0x300d}, {0xff64, 0x3001},
        {0xff65, 0x30fb}, {0xff66, 0x30f2}, {0xff67, 0x30a1}, {0xff68, 0x30a3}, {0xff69, 0x30a5},
        {0xff6a, 0x30a7}, {0xff6b, 0x30a9}, {0xff6c, 0x30e3},
        {0xff6d, 0x30e5}, {0xff6e, 0x30e7}, {0xff6f, 0x30c3},

        {0xff70, 0x30fc}, {0xff71, 0x30a2}, {0xff72, 0x30a4}, {0xff73, 0x30a6, 0x30F4}, {0xff74, 0x30a8},
        {0xff75, 0x30aa}, {0xff76, 0x30ab, 0x30ac}, {0xff77, 0x30ad, 0x30ae},{0xff78, 0x30af, 0x30b0}, {0xff79, 0x30b1, 0x30b2},
        {0xff7a, 0x30b3, 0x30b4}, {0xff7b, 0x30b5, 0x30b6}, {0xff7c, 0x30b7, 0x30b8},
        {0xff7d, 0x30b9, 0x30ba}, {0xff7e, 0x30bb, 0x30bc}, {0xff7f, 0x30bd, 0x30be},

        {0xff80, 0x30bf, 0x30c0}, {0xff81, 0x30c1, 0x30c2}, {0xff82, 0x30c4, 0x30c5}, {0xff83, 0x30c6, 0x30c7}, {0xff84, 0x30c8, 0x30c9},
        {0xff85, 0x30ca}, {0xff86, 0x30cb}, {0xff87, 0x30cc}, {0xff88, 0x30cd}, {0xff89, 0x30ce},
        {0xff8a, 0x30cf, 0x30d0, 0x30d1}, {0xff8b, 0x30d2, 0x30d3, 0x30d4}, {0xff8c, 0x30d5, 0x30d6, 0x30d7},
        {0xff8d, 0x30d8, 0x30d9, 0x30da}, {0xff8e, 0x30db, 0x30dc, 0x30dd}, {0xff8f, 0x30de},

        {0xff90, 0x30df}, {0xff91, 0x30e0}, {0xff92, 0x30e1}, {0xff93, 0x30e2}, {0xff94, 0x30e4},
        {0xff95, 0x30e6}, {0xff96, 0x30e8}, {0xff97, 0x30e9}, {0xff98, 0x30ea}, {0xff99, 0x30eb},
        {0xff9a, 0x30ec}, {0xff9b, 0x30ed}, {0xff9c, 0x30ef},
        {0xff9d, 0x30f3}, {0xff9e, 0x309b}, {0xff9f, 0x309c}
    };

    /** �}�X�N���镶��. */
    private static final char MASK_CHAR = '*';

    /***
     * �ΏۂƂȂ镶����Ƀ}�X�N��������.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param c �}�X�N����.
     * @return �}�X�N�Ńt�B���^�����O���ꂽ�������Ԃ�.
     */
    public static String getMaskFilterString(String str, char c) {
        if (isString(str)) {
            int len = str.length();
            StringBuffer sb = new StringBuffer(len);

            for (int i = 0; i < len; i++) {
                sb.append(c);
            }

            return sb.toString();
        }

        return null;
    }

    /**
     * �Ώە�����Ƀ}�X�N��������.
     *
     * @param str �ΏۂƂȂ镶����.
     * @return �}�X�N�Ńt�B���^�����O���ꂽ�������Ԃ�.
     */
    public static String getMaskFilterString(String str) {
        return getMaskFilterString(str, MASK_CHAR);
    }

    /**
     * ��������Ɋ܂܂��]���ȋ�(�S�p�܂�)�̔r��, �S�p(���p)������̓�����s��.
     * ������ƕ�����̊Ԃ�1�ȏ�̋󔒂��������ꍇ, ��̔��p�X�y�[�X�Ƃ��Ĉ�����.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param conv �ϊ����镶���t�H�[�}�b�g�̎w��.
     * @return ���K�����ꂽ�������Ԃ�. ���ʕ�����null������̏ꍇ��null��Ԃ�.
     */
    public static String trim(String str, int conv) {
        if (isString(str)) {
            String out = StringUtil.replaceAll(str, "�@", " ");

            out = out.replaceAll("\\s+", " ");
            out = StringUtil.toConvertString(out, conv);
            out = out.trim();

            return out;
        }

        return null;
    }

    /**
     * ���l�f�[�^���J���}��؂�ɂ���.
     *
     * @param num �ΏۂƂȂ鐔�l.
     * @return �u����̐���������.
     */
    public static String getNumberFormat(int num) {
        return NumberFormat.getInstance().format(num);
    }

    /**
     * �n���ꂽ������null, �܂��͋󕶎�(���p�󔒂��܂�)�̏ꍇ, ��2�����Ɏw�肵���u���������Ԃ�.
     * �܂������񂪎w�肳��Ă����ꍇ��, �n���ꂽ����������̂܂ܕԂ�.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param to �u�����镶����.
     * @return �u����̕�����.
     */
    public static String getReplaceString(String str, String to) {
        if (str == null || (str != null && str.trim().length() == 0)) {
            return to;
        }

        return str;
    }

    /**
     * �N�H�[�g�����ւ̃o�b�N�X���b�V���̕t��.
     *
     * @param input �ΏۂƂȂ镶����.
     * @return �o�b�N�X���b�V�����t�����ꂽ������.
    */
    public static String getSQLString(String str) {
        if (StringUtil.isString(str)) {
            str = replaceAll(str, "'", "''");
            str = replaceAll(str, "\\\\", "\\\\\\\\");
        }

        return str;
    }

    /**
     * ������̒u�����s�� (String#replaceAll��������).
     *
     * @param input �ΏۂƂȂ镶����.
     * @param from �u�����镶����.
     * @param to �u��������.
     * @return �w�肵�������Œu�����ꂽ�������Ԃ�.
     */
    public static String replaceAll(String str, String from, String to) {
        int lenFrom;

        if (str == null) {
            return null;
        } else if (from == null || (lenFrom = from.length()) == 0 || to == null) {
            return str;
        }

        StringBuffer buf = new StringBuffer(str);
        int lenTo = to.length();
        int pos = buf.indexOf(from);

        while (pos >= 0) {
            buf.replace(pos, pos + lenFrom, to);
            pos = buf.indexOf(from, pos + lenTo);
        }

        return buf.toString();
    }

    /**
     * ��������w�肵���e�ʂɃJ�b�g��, �T�t�B�b�N�X������(...)��t������.
     *
     * @param str �ΏۂƂȂ镶����.
     * @param cut �؂��镶���� (+3�����̃T�t�B�b�N�X�����񂪕t�������).
     * @return �w�肵���T�C�Y�ɃJ�b�g���ꂽ�������Ԃ�.
     */
    public static String getCutString(String str, int cut) {
        int len;
        if (str == null || (len = str.length()) == 0 || len < cut) {
            return str;
        }

        return str.substring(0, cut) + "...";
    }

    /**
     * ������̍��[�Ɏw�蕶������w�蕶�������Ŗ��߂�.
     * (Oracle��LPAD�֐����G�~�����[�g)
     *
     * @param val �ΏۂƂȂ镶����.
     * @param n �u�����镶����.
     * @param s �u�����镶����.
     * @return
     */
    static public String getLPad(String str, int n, String to) {
        int len = str.length();
        if (len < n) {
            for (int i = len; i < n; i++) {
                str = to.concat(str);
            }
        }

        return str;
    }

    /**
     * �w�肳�ꂽ�l��, ������ł��邩�ǂ����𔻒肷��.
     *
     * @param str �ΏۂƂȂ镶����.
     * @return 1�����ȏォ��Ȃ镶����ł����true, null, ���邢�͋󔒕����ł����false��Ԃ�.
     */
    static public boolean isString(String str) {
        if (str != null && str.length() > 0) {
            return true;
        }

        return false;
    }

    /**
     * ��������w�肳�ꂽ�`���ɕϊ�����.
     *
     * @param input �ΏۂƂȂ镶����.
     * @param type �ϊ����镶���t�H�[�}�b�g�̎w��.
     * @return �ϊ����ꂽ������.
    */
    public static String toConvertString(String input, final int type) {
        StringBuffer sb = new StringBuffer();
        char c;
        int m = type;
        int len = input.length();

        // ���{��S�p�L���𔼊p�L���ɒu��.
        if ((m & 128) == 128) {
            for (int i = 0; i < len; i++) {
                c = input.charAt(i);

                switch (c) {
                    // "�u"
                    case 0x300C:
                        sb.append((char) 0xFF62);
                        break;
                    // "�v"
                    case 0x300D:
                        sb.append((char) 0xFF63);
                        break;
                    // "�B"
                    case 0x3002:
                        sb.append((char) 0xFF61);
                        break;
                    // "�A"
                    case 0x3001:
                        sb.append((char) 0xFF64);
                        break;
                    // "�E"
                    case 0x30FB:
                        sb.append((char) 0xFF65);
                        break;
                    // "�["
                    case 0x30FC:
                        sb.append((char) 0xFF70);
                        break;
                    // "�J"
                    case 0x309B:
                        sb.append((char) 0xFF9E);
                        break;
                    // "�K"
                    case 0x309C:
                        sb.append((char) 0xFF9F);
                        break;
                    default:
                        sb.append(c);
                }
            }

            m -=128;
        }

        // ���{�ꔼ�p�L����S�p�L���ɒu��.
        if ((m & 64) == 64) {
            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);

                switch (c) {
                    // "�"
                    case 0xFF62:
                        sb.append((char) 0x300C);
                        break;
                    // "�"
                    case 0xFF63:
                        sb.append((char) 0x300D);
                        break;
                    // "�"
                    case 0xFF61:
                        sb.append((char) 0x3002);
                        break;
                    // "�"
                    case 0xFF64:
                        sb.append((char) 0x3001);
                        break;
                    // "�"
                    case 0xFF65:
                        sb.append((char) 0x30FB);
                        break;
                    // "-"
                    case 0xFF70:
                        sb.append((char) 0x30FC);
                        break;
                    // "�"
                    case 0xFF9E:
                        sb.append((char) 0x309B);
                        break;
                    // "�"
                    case 0xFF9F:
                        sb.append((char) 0x309C);
                        break;
                    default:
                        sb.append(c);
                }
            }

            m -=64;
        }

        // �Љ����𕽉����ɒu��.
        if ((m & 32) == 32) {
            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);
                sb = (c >= 0x30a1 && c <= 0x30f3) ? sb.append((char) (c - 0x60)) : sb.append(c);
            }

            m -= 32;
        }

        // ��������Љ����ɒu��.
        if ((m & 16) == 16) {
            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);
                sb = (c >= 0x3041 && c <= 0x3093) ? sb.append((char) (c + 0x60)) : sb.append(c);
            }

            m -= 16;
        }

        // �S�p�J�i�𔼊p�J�i�ɒu��.
        if ((m & 8) == 8) {
            int x;

            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);

                if (c >= 0x30a1 && c <= 0x30ef || c >= 0x30f2 && c <= 0x30f4) {
                    nextChar:
                    for (int j = 0; j < 63; j++) {
                        x = JP_CHAR_TBL[j].length;

                        for (int k = 0; k < x; k++) {
                            if (c == JP_CHAR_TBL[j][k]) {
                                switch (k) {
                                    case 1:
                                        sb = sb.append(JP_CHAR_TBL[j][0]);
                                        break;
                                    case 2:
                                        sb = sb.append(JP_CHAR_TBL[j][0]).append((char) 0xff9e);
                                        break;
                                    case 3:
                                        sb = sb.append(JP_CHAR_TBL[j][0]).append((char) 0xff9f);
                                        break;
                                }

                                break nextChar;
                            }
                        }
                    }
                } else {
                    sb = sb.append(c);
                }
            }

            m -= 8;
        }

        // ���p�J�i��S�p�J�i�ɒu��.
        if ((m & 4) == 4) {
            int p = 0;
            int x;

            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);
                x = JP_CHAR_TBL[p].length;

                if (c == 0xff9e && x >= 3) {
                    sb = sb.deleteCharAt(sb.length() - 1);
                    sb = sb.append(JP_CHAR_TBL[p][2]);
                } else if (c == 0xff9f && x == 4) {
                    sb = sb.deleteCharAt(sb.length() - 1);
                    sb = sb.append(JP_CHAR_TBL[p][3]);
                } else if (c >= 0xff60 && c <= 0xff9f) {
                    for (int j = 0; j < 63; j++) {
                        if (JP_CHAR_TBL[j][0] == c) {
                            sb.append(JP_CHAR_TBL[p = j][1]);
                            break;
                        }
                    }
                } else {
                    sb = sb.append(c);
                }
            }

            // ���p�X��������S�p�ɒu��.
            if ((type & 32) == 32) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());

                for (int i = 0; i < len; i++) {
                    c = input.charAt(i);
                    sb = (c >= 0x30a1 && c <= 0x30f3) ? sb.append((char) (c - 0x60)) : sb.append(c);
                }
            }

            m -= 4;
        }

        // �p���L�������𔼊p�����ɒu��.
        if ((m & 2) == 2) {
            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);
                sb = (c >= 0xff01 && c <= 0xFF5A) ? sb.append((char) (c - 0xfee0)) : sb.append(c);
            }

            m -= 2;
        }

        // �Љ����𕽉����ɒu��
        if ((m & 1) == 1) {
            if (sb.length() > 0) {
                input = sb.toString();
                sb = sb.delete(0, len = sb.length());
            }

            for (int i = 0; i < len; i++) {
                c = input.charAt(i);
                sb = (c >= 0x21 && c <= 0x7e) ? sb.append((char) (c + 0xfee0)) : sb.append(c);
            }

            m -= 1;
        }

        return sb.toString();
    }
}
