import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void twoSum() {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] result = TwoSum.twoSum(nums,target);
        assertEquals(result[0], 0);
        assertEquals(result[1], 1);
    }
}