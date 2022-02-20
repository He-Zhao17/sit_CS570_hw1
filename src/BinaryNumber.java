public class BinaryNumber {
    //Little-Endian
    private int length;
    private int data[];
    private boolean overflow;


    // A constructor BinaryNumber(int length) for creating a binary number of
    // length and consisting only of zeros.
    // via He Zhao
    public BinaryNumber(int length) {
        if (length <= 0) {
            System.out.println("Error: No 0 or nagative lenth.");
            System.exit(1);
        }
        this.length = length;
        data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = 0;
        }
    }

    /*
    * A constructor Binary Number(String str) for creating a binary number
    * given a string.
    * via He Zhao
    * */
    public BinaryNumber(String str) {
        this.length = str.length();
        data = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            if (Character.getNumericValue(str.charAt(i)) == 0 || Character.getNumericValue(str.charAt(i)) == 1) {
                data[i] = Character.getNumericValue(str.charAt(i));
            } else {
                System.out.println("Error: The string is not a binary number.");
                return;
            }
        }
    }

    /*
    * An operation int getLength() for determining the length of a binary number.
    * via He Zhao
    * */
    public int getLength() {
        return this.length;
    }

    /*
    * An operation int getDigit(int index) for obtaining a digit of a binary
    * number given an index. The starting index is 0. if the index is out of
    * bounds, then a message should be printed on the screen indicating this
    * fact.
    * via He Zhao
    * */
    public int getDigit(int index) {
        if (index < 0) {
            System.out.println("Error: No negative index.");
            System.exit(1);
        }
        if (index >= this.length) {
            System.out.println("Error. The index is out of bound.");
            return -1;
        } else {
            return data[index];
        }
    }

    /*
    * An operation int toDecimal() for transforming a binary number to its
    * decimal notation.
    * via He Zhao
    * */
    public int toDecimal() {
        int dec = 0;
        for (int i = 0; i < this.length; i++) {
            dec = dec + data[i] *  (int)Math.pow(2, i);
        }
        return dec;
    }

    /*
    * An operation void shiftR(int amount) for shifting all digits in a binary
    * number any number of places to the right, as indicated by a parameter
    * amountToShift. The new digit should be 0.
    * via He Zhao
    * */
    public void shiftR(int amount) {
        if (amount < 0) {
            System.out.println("Error: the amount is above 0.");
            return;
        }
        this.length = this.length + amount;
        int[] oldData = this.data;
        data = new int[this.length];
        for (int i = 0; i < amount; i++) {
            data[i] = 0;
        }
        for (int i = amount; i < this.length; i++) {
            data[i] = oldData[i - amount];
        }
    }

    /*
    * Adding two binary numbers, one is the binary number that receives the
    * message and the other is given as a parameter.
    * via He Zhao
    * */
    public void add(BinaryNumber aBinaryNumber) {
        clearOverflow();
        if (aBinaryNumber.length != this.length) {
            System.out.println("Error. These 2 add number`s length is not equal.");
            return;
        }
        // Carried Flag
        boolean Ca = false;
        int[] sum = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            int sum1 = 0;
            if (Ca) {
                sum1 = this.data[i] + aBinaryNumber.data[i] + 1;
            } else {
                sum1 = this.data[i] + aBinaryNumber.data[i];
            }
            if (sum1 > 1) {
                Ca = true;
                sum[i] = sum1 % 2;
            } else {
                sum[i] = sum1;
            }
        }
        if (Ca) {
            System.out.println("Overflow.");
            return;
        }
        this.data = sum;
        clearOverflow();
    }

    /*
    * An operation clearOverFlow() that clears the overflow flag.
    * via He Zhao
    * */
    public void clearOverflow() {
        this.overflow = false;
    }

    /*
    * An operation String toString() for transforming a binary number to a String.
    * via He Zhao
    * */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            sb.append(this.data[i]);
        }
        return sb.toString();
    }


    public static void main (String[] args) {

        // Test constructor of int length
        BinaryNumber bn = new BinaryNumber(21);
        System.out.println("Now test the constructor of length:");
        for (int i = 0; i < bn.length; i++) {
            System.out.print(bn.data[i] +  " ");
        }

        //Test constructor of str
        bn = new BinaryNumber("1011");
        System.out.println("\n");
        System.out.println("Now test the constructor of str:");
        for (int i = 0; i < bn.length; i++) {
            System.out.print(bn.data[i] +  " ");
        }

        //Test the getLength()
        System.out.println("\n\nNow test the getLength");
        System.out.println(bn.getLength());

        //Test the getDigit()
        System.out.println("\n\nNow test the getDigit()");
        for (int i = 0; i < bn.length; i++) {
            System.out.println("The " + i + " digit number is " + bn.getDigit(i));
        }

        //Test the toDecimal()
        System.out.println("\n\nNow test the toDecimal():");
        System.out.println(bn.toDecimal());

        //Test the shiftR()
        System.out.println("\n\nNow test the shiftR(4)");
        bn.shiftR(4);
        for (int i = 0; i < bn.length; i++) {
            System.out.print(bn.data[i] + " ");
        }

        //Test the add()
        System.out.println("\n\nNow test the add():");
        BinaryNumber bn1 = new BinaryNumber("11111111");
        BinaryNumber bn2 = new BinaryNumber("11111");
        BinaryNumber bnAdd = bn;
        bnAdd.add(bn1);
        System.out.print("\n00001011 + 11111111 = ");
        System.out.print(bnAdd.toString());
        bnAdd = bn;
        System.out.print("\n");
        bnAdd.add(bn2);
        System.out.print("\n00001011 + 11111 = ");
        System.out.print(bnAdd.toString());
    }

}
