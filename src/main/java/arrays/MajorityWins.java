package arrays;

import org.junit.Test;

public class MajorityWins {

    @Test
    public void test(){

       int[] arr = new int[]{630,775,709,72,136,612,268,461,38,115,239,111,557,659,728,259,83,879,39,143,793,299,427,452,340,153,915,837,525,745,244,587,912,597,767,708,235,195,531,482,260,918,414,153,512,2,105,772,271,892,415,509,857,674,648,351,926,356,654,313,427,564,633,105,738,636,754,976,262,621,433,357,328,83,232,798,219,75,894,272,80,684,97,621,334,555,625,478,394,475,667,248,918,705,973,158,8,174,572,305,522,96,761,745,850,151,819,87,69,479,916,368,568,152,341,557,94,726,508,927,429,394,341,998,754,121,96,963,195,18,228,131,385,376,886,582,982,453,171,17,221,522,379,765,565,543,568,831,11,660,223,41,743,147,328,751,446,981,885,291,948,564,970,553,887,184,251,523,340,859,751,564,58,993,266,993,50,129,888,885,642,898,935,662,324,91,540,91,813,726,219,722,637,503,303,71,324,914,776,274,375,371,941,429,90,108,203,540,766,490,583,654,382,186,758,288,204,47,54,560,539,766,
                74,97};
        System.out.println(majorityWins(arr, 222, 74, 97));
    }

    public int majorityWins(int arr[], int n, int x, int y) {
        // code here
        int xCount = 0;
        int yCount = 0;
        for(int i=0;i<n;i++){
            if(arr[i] == x){
                xCount++;
            } else if(arr[i]==y){
                yCount++;
            }
        }

        if(xCount>yCount || (xCount == yCount && x <y)){
            return x;
        } else {
            return y;
        }
    }
}
