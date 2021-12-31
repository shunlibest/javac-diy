package com.shunli.FileSystem;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;

public class FileUtil {


    private static final ByteBufferCache byteBufferCache = new ByteBufferCache();

    /**
     * Make a byte buffer from an input stream.
     */
    public static ByteBuffer makeByteBuffer(InputStream in)
            throws IOException {
        int limit = in.available();
        if (limit < 1024) limit = 1024;
        ByteBuffer result = byteBufferCache.get(limit);
        int position = 0;
        while (in.available() != 0) {
            if (position >= limit)
                // expand buffer
                result = ByteBuffer.
                        allocate(limit <<= 1).
                        put((ByteBuffer) result.flip());
            int count = in.read(result.array(),
                    position,
                    limit - position);
            if (count < 0) break;
            result.position(position += count);
        }
        return (ByteBuffer) result.flip();
    }

    private static class ByteBufferCache {
        private ByteBuffer cached;

        ByteBuffer get(int capacity) {
            if (capacity < 20480) capacity = 20480;
            ByteBuffer result =
                    (cached != null && cached.capacity() >= capacity)
                            ? (ByteBuffer) cached.clear()
                            : ByteBuffer.allocate(capacity + capacity >> 1);
            cached = null;
            return result;
        }

        void put(ByteBuffer x) {
            cached = x;
        }
    }


    public static CharBuffer decode(ByteBuffer inbuf, boolean ignoreEncodingErrors) {
        String encodingName = getEncodingName();
        CharsetDecoder decoder;
        try {
            decoder = FileUtil.getDecoder(encodingName, ignoreEncodingErrors);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
            System.out.println("unsupported.encoding" + encodingName);
            return (CharBuffer) CharBuffer.allocate(1).flip();
        }

        // slightly overestimate the buffer size to avoid reallocation.
        float factor =
                decoder.averageCharsPerByte() * 0.8f +
                        decoder.maxCharsPerByte() * 0.2f;
        CharBuffer dest = CharBuffer.
                allocate(10 + (int) (inbuf.remaining() * factor));

        while (true) {
            CoderResult result = decoder.decode(inbuf, dest, true);
            dest.flip();

            if (result.isUnderflow()) { // done reading
                // make sure there is at least one extra character
                if (dest.limit() == dest.capacity()) {
                    dest = CharBuffer.allocate(dest.capacity() + 1).put(dest);
                    dest.flip();
                }
                return dest;
            } else if (result.isOverflow()) { // buffer too small; expand
                int newCapacity =
                        10 + dest.capacity() +
                                (int) (inbuf.remaining() * decoder.maxCharsPerByte());
                dest = CharBuffer.allocate(newCapacity).put(dest);
            } else if (result.isMalformed() || result.isUnmappable()) {
                // bad character in input

                // skip past the coding error
                inbuf.position(inbuf.position() + result.length());

                // undo the flip() to prepare the output buffer
                // for more translation
                dest.position(dest.limit());
                dest.limit(dest.capacity());
                dest.put((char) 0xfffd); // backward compatible
            } else {
                throw new AssertionError(result);
            }
        }
        // unreached
    }

    public static CharsetDecoder getDecoder(String encodingName, boolean ignoreEncodingErrors) {
        Charset cs = Charset.forName(encodingName);
        CharsetDecoder decoder = cs.newDecoder();

        CodingErrorAction action;
        if (ignoreEncodingErrors)
            action = CodingErrorAction.REPLACE;
        else
            action = CodingErrorAction.REPORT;

        return decoder
                .onMalformedInput(action)
                .onUnmappableCharacter(action);
    }

    public static String getEncodingName() {
        return new OutputStreamWriter(new ByteArrayOutputStream()).getEncoding();
    }


}
