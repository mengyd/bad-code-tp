public class Main {

    /**
     * Abstract cipher
     */
    interface ICipher {
        String encrypt(String message, String key);
        String decrypt(String message, String key);
    }

    /**
     * Caesar cipher implementation. This is a substitution cipher in which each letter
     * is replaced by a letter shifted by the provided key. Shifting the other way with
     * the same key decrypt the encrypted message
     */
    public static class CaesarCipher implements ICipher {

        public String encrypt(String toEncrypt, String key) {
            // Get the real key
            int realKey = Integer.valueOf(key);
            char[] result = toEncrypt.toCharArray();
            for (int i = 0; i < result.length; i++) {
                int max = Character.MAX_VALUE;
                // Handle character overflow
                result[i] = (char) ((((result[i] + realKey) % max) + max) % max);
            }
            return new String(result);
        }

        public String decrypt(String toDecrypt, String key) {
            int realKey = Integer.valueOf(key);
            char[] result = toDecrypt.toCharArray();
            for (int i = 0; i < result.length; i++) {
                int max = Character.MAX_VALUE;
                result[i] = (char) ((((result[i] - realKey) % max) + max) % max);
            }
            return new String(result);
        }
    }

    /**
     * Entry point
     */
    public static void main(String[] args) {
        if(args[0].equals("encrypt")) {
            if(args.length < 3)
                System.out.println("Usage : encrypt|decrypt message key");
            else
                System.out.println(new CaesarCipher().encrypt(args[1], args[2]));
            System.exit(0);
        } else if(args[0].equals("decrypt")) {
            if(args.length < 3)
                System.out.println("Usage : encrypt|decrypt message key");
            else
                System.out.println(new CaesarCipher().decrypt(args[1], args[2]));
            System.exit(0);
        } else if(args[0].equals("help")) {
            System.out.println("Usage : encrypt|decrypt message key");
            System.out.println("Caesar cipher. This is a substitution cipher in which each letter\n" +
                               "is replaced by a letter shifted by the provided key. Shifting the\n" +
                               "other way wit the same key decrypt the encrypted message.");
            System.exit(0);
        }

        System.out.println("Usage : encrypt|decrypt message key");
        System.exit(1);
    }

}
