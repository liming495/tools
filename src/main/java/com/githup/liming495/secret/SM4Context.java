package com.githup.liming495.secret;

/**
 * description
 *
 * @author Guppy
 * @version version
 * @since 2022/2/15 11:04
 */
public class SM4Context {
    public int mode;

    public long[] sk;

    public boolean isPadding;

    public SM4Context()
    {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new long[32];
    }

}
