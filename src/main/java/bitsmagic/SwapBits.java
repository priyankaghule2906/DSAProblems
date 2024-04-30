package bitsmagic;

public class SwapBits {
    static int swapBits(int x) {
        // Get all even bits of x
        System.out.println("even bits" + Integer.toBinaryString(0xAAAAAAAA));
        int even_bits = x & 0xAAAAAAAA;
        System.out.println("x        " + Integer.toBinaryString(x));
        System.out.println("even bits" + Integer.toBinaryString(even_bits));
        // Get all odd bits of x
        System.out.println("odd bits" + Integer.toBinaryString(0x55555555));
        int odd_bits = x & 0x55555555;
        System.out.println("x       " + Integer.toBinaryString(x));
        System.out.println("odd bits" + Integer.toBinaryString(odd_bits));
        // Right shift even bits
        even_bits >>= 1;

        System.out.println("after right shit by 1"+ Integer.toBinaryString(even_bits));

        // Left shift even bits
        odd_bits <<= 1;

        System.out.println("after left shit by 1"+ Integer.toBinaryString(odd_bits));
        // Combine even and odd bits

        System.out.println("final result        "+ Integer.toBinaryString(even_bits | odd_bits));
        return (even_bits | odd_bits);
    }

    public static void main(String[] args) {
        // Example usage:
        int N1 = 23;
        int N2 = 2;

        System.out.println(swapBits(N1));  // Output: 43
        System.out.println(swapBits(N2));  // Output: 1
    }
}
