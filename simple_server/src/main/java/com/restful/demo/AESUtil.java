package com.restful.demo;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 接口加密 解密
 * @author yang_yunjie
 * @date 2017-10-23 17:23:21
 *
 */
public class AESUtil {

    private static String key = "1234567890123111";

    public static String aesEncrypt(String str) {
        if (str == null || key == null) {
            return str;
        }
        byte[] bytes;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            bytes = cipher.doFinal(str.getBytes("utf-8"));
            return new BASE64Encoder().encode(bytes).replace("\r\n", "");
        } catch (Exception e) {
            return str;
        }
    }

    public static String aesDecrypt(String str) {
        if (str == null || key == null) {
            return str;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
            byte[] bytes = new BASE64Decoder().decodeBuffer(str);
            bytes = cipher.doFinal(bytes);
            return new String(bytes, "utf-8");
        } catch (Exception e) {
            return str;
        }
    }
    
    public static void main(String[] args) {
    	String str = "oZWs3bflN3ZgkP3ScgccaUWNgaRzsg74XuN1UECVCd1Qq8hBXxWKqGAz0EXe/bzacmm9/gURCEa8HTX6gUCzt88dXfgLWZjWnCSLfDlfnIWAAIYDGVeCK0kmz47X3CC3w9H69GYSvyPL5/HsnqaCP9l9sZ1/%2bOk/h0FDtF34rTAaT%2bDe6E8ApwdYU4pKL0A0p5zW/2sFwwW%2bO8Ooa3k%2bIUT04Ddvjapv1L05yg3N6y5ksck90og6uSvhG80UujX5F2Pbm7IJu7ywvwNR9ZAQgWUdETEBQBM2knfUL4E8UtlU2JLx2SWZBIiwL99j2HBg/7vlKPQTW2nyM0TnFiyjP/4zz0OA5qa4wd/TqyDj1Cof2QiYtwx/SM7baxl89E4mx/KRSxTIDGK00XAiX2LBAeY%2blNib1grYRna/MeRYqzP%2boP44XqtYISiL9h06mtM%2botvZtTiQwCuySIPKwTgY5TE83g8oKGsXMSNm9nug7blUrbbE67d0hxRpZ3p5P5b5B05oO9ndphW%2bValhX5Xc3B0D1qZ4U9fWVI/nuYu%2bO4L9K7E7XaTC8E35bv/gLRxycFfBl9rxleZFuCWRMxLQggZKJjGZFPUFgXE%2b5NjsAyBJKlqHDorhVv6SX/cSKrjxS/jzOA3/qZbnmq4NsPrbvzb3UmcoEEsnllEkXEUUu/UaBxy5GhLn0b%2b0JJje5zxlHLW9l19AX6XKpAf11NOx3h68zZOeoC0XXIgRosPqcgYYE8PG%2bf41%2bMbjswyLH8MBG0ThKJZeQCx81zeusvEGfRXshRtmZyN1PfLmWSlrg1Pgx%2bHTbwhiYUT7aOKCGE%2bUel7F0dZ7Uk1nTNEWBjaC/%2bKRfQBAbzianG7WZelwn2%2bkP9/yuJ29dFsXTQaE5tN6Mv8jrxhnWBmwWWRMzcOy/wpdvcyT/mOv/BYn1JbGVbHNCN/BzK8/OuodKXAKlSj5cX5HmqRC7buTIokU78hYpFEz85pyMIjCVdWHd83UzP58PfltfNmqDyJAozhxSnINkKw5tl%2bOfd4R2dzkDldv50IDO9z3i4eZjxVDQrrVGRCxiSDKKbPRD2tJDuVzUWD9nGb7GS9GCo6sd3eBS3E3K3ZBKIJKXfmpRR0Rvg2Zo1M28j6%2bO723PGcrmc%2b8AxIGxkmPp432/2L9kgaQm1daSQ4BEzTZnlcGjxB3tdg4ZQGmmi1DNtEe/H2qMQfJCp7RcWALG1KonmPaEt5UWfMo0H6w1TjPpNRBFDBtUDT492dXlEE3XH3kO7pua0MZx6S2JB05sok1M8t7W5IwKCP0g1%2blGpcGykKmO7ixuprVVCw8FvJBmlQajeNcOyOaiYucuUbVM9Ld4ysJCneCPY29LPzLUW2QhaeJ07ZyTY/G3iOzC5%2bMmYnccYQY8wvzqu6gum07LMRJyTJ2hsjqzExSdfL7fs61fFTSSXsQTEvDlUe/tG2MjdOV048bICaSDwWbRN4uwu12ZBgs5x8uKACfA%2bDnM6aK4P5eZFsy%2b1GZy/Z981RqVUfcmy44o4tIcQ4pSx%2bBDDCo2WTEIXO4dy9s7HKbDrpcZK15jQNkXhV6fd%2bm0gbL1W3WVmdTf2z4%2bCX9xt6UxGcujMJ8vkdFaPjM19L37hnVGNKU%2bU0nSx%2baD08FD2/bDRoFghZ9b2ctvrXEXvUEYeq2Lu7QxAPKoAi36lL3lZ/M3YuAjPnW5XFj463uvZSv0cWztV7S1CV/HiSCHX4hMxk9OysWbwX/pZ8Rx5AhLWNo7WnYSaCxM5yddXr8sogE5muvz1wU4dx8WrzCdKCp2ollOCOIWE6kvSJUHfve8iqj0HyMCp/yUSDUVFR2tEmsL/V2wExzBDz7UbmOTMq0v3llR8aGnl86h4mnL6Hrrn%2b%2bZc5Cl5ce8UwKW4mBCY4bJhzAEKn7VYvvIkaVM28v9dwnHjjCYLc5jNkG5jQ4LBHcWxHapmFrPKFFhn7yf1QXbR0LFYytc1KRyHMfs6iXTPYIinwxwbsRbA9QNeBSYXbFcXAhtH/1402GE/lQuikF7H42z9Ltqj5R8vwYTpRweAU9CinogOm0ZHGr2Ua3YYYPnp4qB3SqYDCzbh/D6663Qk7lIlIDaPXvtKpMUPma5DIUyg2374yZG8LsOOq4y2n412vcSKWf/kwnLS6NekxyXmmWa3pr%2bkUpSEvG/XTzFbWU9EOfRRyoQtFM3dJ/vt0i/AgAJ1jncEOfOt8Pi3CWCmd4ii1y9da6tcMF2JM4beTA8uFlpgGntyBpxjZQ4fPN8rW1j0Ho9VFKVAI5lrUOjoLO5ghoO09EH68UGo2SOD9QPjaycrbLAUVAZUN%2b4xGAPiiocEFNJg4bSltLfFtNJiTIO/KhuLcKoCuv5LtGWJgsuOl3BhY175SqU14EHYhjkT3dIEez7DeaqXF3wLxS99vIhZ8OWAf6a4APH85VEp3tS3yfbe6RWHxebem1f9%2bTN65HAEWV6c1jT/jgtdEANNNgTONoiU9Q3e7hqany9q%2bZIwy8Nz2Do3/8Aml8kov7/qZ865Md8jBV8xpBHHaCprJO7ZLVCUSDoY%2bxUNQo5BgJwTtqNXPX6XmVrV8CgVPcT7GAaSD7Z/4jKoyNZOohKUXkF448fpGwHiTeIRm95xVp7XCTPW524L0Sx5gNB6fBIQrNDqd4ADzKF/s7Inb%2bwMKR7L8GVF9OM2lpGrlRZD6vAsA1YhgBsrTxSlL0mFDy52uyStLjwfXH6Bwbxml95/UPfZQxNrbY6ElCHEDkThmnEpbH7q76RZ62p7OlHKPqr4/QF6vETll%2b3m49W/TF%2b3ZajVnQ6t5PVmgjzSCBLB/eQ0W9i9Cp/ItMG8Eg4kL2hx8Jn6J/g8mXbTKGG57O86YU0aVWn7UiPrZgO8J7n1klsJoZqIA4j9DUhdgl8/Gk5ao2kZ5dIy2azwgcqEdPKdQ5VHYo0Et%2b7/Xwes92KIDuWecgNNXsRelvQuaqS92qxJOUDP2W9aL6DTP9eW5ZeoKMvJJmkSBCH3sCTPjO0fRiFhIc6rvPH%2bqrJJ1TCoAIuyEM0qSIJCTW96ckYEdP/9zW4wc3ALRx3U/wZ9Dx4a6Zh/0aaVAfubhAkmq0OjjINV8YA4cn0XR2HRrmbDJD77K6bTAuTfneanyeasyZZ3fN0/lQ397soCUFK5jQUk2DFb7FFUu1iMuTrNWWgac0VULQbIpg6sMd1fb4aGJ0MUMapl72xH8wAUjnjoYqMhm7C7A4bB0h9/ozU2jQUZbfIPOLvUD6qeu9XPO6D7IGJEiSi8HXF7j8VLKb1AYcCSf/qXqqVOEB8dwU4FyUx7otGFiBs/bkg4/xcvncHLW9l19AX6XKpAf11NOx3ocktNmUIc7jjv1xLVe0EnMYE8PG%2bf41%2bMbjswyLH8MBG0ThKJZeQCx81zeusvEGfRXshRtmZyN1PfLmWSlrg1P/cHDyGrZ1WMqXPmLN7I/Rel7F0dZ7Uk1nTNEWBjaC/yHo2G7ZSkcL6dirMpifkFzku0ZYmCy46XcGFjXvlKpTXgQdiGORPd0gR7PsN5qpcXfAvFL328iFnw5YB/prgA%2bRQdWQvepS75qJlbiFDmiB6bV/35M3rkcARZXpzWNP%2bF7pdB1FX3exZ7vRd2u1DwbkPL/ARZTRYhJgmwrnfyWCADFFSlrpFdeuAUe5Djgwx//wIQ61y2ReJj1e6Ffumil8FwfbUFl6QtQNAq/tCxsK83wGh1zS/RQ64ZeQY8aerWjLk7Xj4JMhMTswy0K6/FoK%2blm0RUTkHWddb/OPaj73Qw1w75Iun/pT89%2b//4WeSz4AN3OtecxOwCX1i6mcyYVd0PJN9k0nvVpB56VjJ%2bAwbYpxW8f8EEfNeV881erV0/fJhk1pXbxCxrQG/ycBdK80Il0Dey7EpDuR9kvFnB/0xADAeY99ZiZax4lud4A9Mqby1NG%2beQJK9rdj8v6bJrcctb2XX0BfpcqkB/XU07HeNxl3wGggvOrnvpa6VDzRdBgTw8b5/jX4xuOzDIsfwwEbROEoll5ALHzXN66y8QZ9Bsrn2%2bkQc4PFY9Bt4lbR5ODH4dNvCGJhRPto4oIYT5R6XsXR1ntSTWdM0RYGNoL/HuXjqvzbRF/7bcCR%2bzxqaTSw7FLoaJmuvCE75JR2/0OltCARHk4wQxYyN6qa0A/yKEG%2bC4Zz6o5rm%2bJIW8D/Dp%2bMhxGc/ndC6wvqkO59QrWo9K38wixIFukUyt8SLEINcTNNGZpiVxqy0i0KJQVKZOhmOBf8BMZ5EePlPHm%2bOt/lYbxMd%2bDMNqa5vMFo58bIBOABxCUBqxWX8aQ8723PA/itB4WImAREmnq4AHrgLqNdQqwhZoPBnfNFjIlAOzxkNWzi66nYWjmzQGwILAAesuwcABV30ukZSMubhJFU9CRqaoqOxpy829Gr77PUIi/5FJLbqqpYI0gzsJM71F4s7/w73kROBmxM6xdllrqS4ApFq6wqdzLM9era3k9qUej/lHUQEqYse0uiSq4q0UXpbLGC8xe8Ew%2boaytyQY44C6Ka7Q1rRf%2bbH0DGQoZD2XOVp2kMfjPFX9Q6CJaCO5RMk8fUsrgP3V6Xc6NGBYX5IKoUeqGbJI2a3KCWLP7GXTDJOBZErRwC4Cpt3uwF8vNRgAevJHtzKnfYK7sAYcqEJUc8/YDUiwmEnd2l3P%2bri1d92FS0wSFWOaXrGP6a0WibMRNN/P19kPnXCANWcOnI5VYLeZ42GVqlbJa3n0lXDep85qpL3arEk5QM/Zb1ovoNM/15bll6goy8kmaRIEIfewJM%2bM7R9GIWEhzqu88f6qsk8z73dwPAgFx7xJXXUxcxnJfvc804z2RhZgCZXQ0jsoqRIWv9EHr1XZ23ZxS8Khwv3EGmyF6djCZ5pqIvFmRx1EbxgCW%2b24VXizGun59VUrO8oH6pJbhc9dvzdRkVlcdYqHufr5i3DtOju7KqzAaBpydm9EF0pnsJrPoggrCEfzzxY7wTHPqhTGHx%2bDKzKJ8gnGKeEd9EQF3gOs%2bNrZIdXrdXUNokfEw3Pd7j5sEwgUXvFUR%2bk8GPhyfBlWcvuH%2bwr5J4PFIZ7fQEj9nNfqHaDFbYtF3aJ86yYnZyF4HRRJtKKrOdw/pQcNGA5wREaHrltDB8SstyxOPvq85b%2bZCKDzUj38h9I6kOoDrJoIaja786aofay49Fn3M%2busYq5o9HlW3VZs5nIJRE0kCA8AANe7uVLe8II4vElo/TegFZ5qEWfiP38sR2/4mcQHGNxQ57cLm4HoHmzZlrO1G872eiasScpKw2wDxfVkopkn65ygc9blFunnfSG07Ph3nQ0ukGjlu%2bxAlR512qp272zx3v1qtcJe%2bYbYL/BSh52LYqUJu/tVmAACxOnQoy/u%2b7Q4vr/2KOwvEzdKK%2bOi9GG8kRWQJkMG1g3Vvk6uQQzj7s8Ro6ws5hUbDfhEczyC4vvcIO960fA0poPJGkAXMYpK5D4UVk%2bSNVkiIVYTVyUVTSx8vSXGgmYUNALl/flkxq1QjfVQn2iS0K5dUbcTZctDtBdwKfX9B1P%2bSOEae1dUBkT8nCAooQAjcyskZ%2b5iLoVkmwXxyE1wQ2p23Y1lGSWEXWF6qM5bq9vjL6n0uDoI171Qu5JhMpRL%2br3zZzEpgABVCSNpkLVDL0VkMuLcX7m0eGmQa6%2b%2bgIWeQnKeTLxQSexBFQpqcULS2xn6fQKrCKWkZYodx8Fn8LPHdhfOmj2kfxftXwyemFG6nY3iaU6%2b1b1Kc/0oz0n4UMbnFhnkR%2b0y/FJkoipExtk45XUfTD78xTeK0epKLvTuDUh3UixurxfQ%2bUJi89D2fUDFrE2EkOalIKKamomea8o4XBZEEOJWP33en/XVPntIzr5Bn/Kdomj0ht0oX7rcTT9aERsGT4KUpA";
    	str = str.replaceAll("%2b", "+");
    	System.out.println(aesDecrypt(str));
//    	System.out.println(b);
    	
	}
}