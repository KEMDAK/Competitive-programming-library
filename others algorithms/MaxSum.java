package other_algorithms;

/*
 * Kadane's Algorithm to find the Max Sum subarray (consecutive elements)
 */
public class MaxSum {
	static final int INF = (int)1e9;

    // for 1D Arrays (Tube)
	static int Array_1D(int[] arr) {
		int n = arr.length;

		int max = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if (sum < 0)
				sum = 0;
			max = Math.max(sum, max);
		}

		return max;
	}

    // for 2D Arrays (Rectangle)
	static int Array_2D(int[][] arr) {
		int n = arr.length;
		int[][] commulative = new int[n][n];

		for(int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				commulative[i][j] = arr[i][j];

				if (i > 0)
					commulative[i][j] += commulative[i-1][j];
				if (j > 0)
					commulative[i][j] += commulative[i][j-1];
				if (i > 0 && j > 0)
					commulative[i][j] -= commulative[i-1][j-1];
			}


			int max = -INF;
			for (int i = 0; i < n; i++) {
				for (int j = i; j < n; j++) {
					int sum = 0;
					for (int row = 0; row < n; row++) {
						if (i > 0)
							sum += commulative[row][j] - commulative[row][i-1];
						else
							sum += commulative[row][j];

						if (sum < 0)
							sum = 0;
						max = Math.max(sum, max);
					}
				}
			}

			return max;
		}


    // for 3D Arrays (cuboid)
		static long Array_3D(int[][][] arr) {
		int a = 0; //length
		int b = 0; //width
		int c = 0; //height

		long commulative[][][] = new long[a][b][c];
		for (int i = 0; i < a; i++)
			for (int j = 0; j < b; j++)
				for (int k = 0; k < c; k++) {
					commulative[i][j][k] = arr[i][j][k];
					if (i > 0)
						commulative[i][j][k] += commulative[i-1][j][k];
					if (j > 0)
						commulative[i][j][k] += commulative[i][j-1][k];
					if (k > 0)
						commulative[i][j][k] += commulative[i][j][k-1];

					if (i > 0 && j > 0)
						commulative[i][j][k] -= commulative[i-1][j-1][k];
					if (i > 0 && k > 0)
						commulative[i][j][k] -= commulative[i-1][j][k-1];
					if (j > 0 && k > 0)
						commulative[i][j][k] -= commulative[i][j-1][k-1];

					if (i > 0 && j > 0 && k > 0)
						commulative[i][j][k] += commulative[i-1][j-1][k-1];
				}

				long max = Integer.MIN_VALUE;
				for (int i = 0; i < a; i++) {
					for (int j = 0; j < b; j++) {
						for (int k = 0; k < c; k++) {
							max = Math.max(max, commulative[i][j][k]);
							for (int i2 = i; i2 < a; i2++) {
								for (int j2 = j; j2 < b; j2++) {
									for (int k2 = k; k2 < c; k2++) {
										long sum = commulative[i2][j2][k2];
										if (i > 0)
											sum -= commulative[i-1][j2][k2];
										if (j > 0)
											sum -=  commulative[i2][j-1][k2];
										if (k > 0)
											sum -= commulative[i2][j2][k-1];

										if (i > 0 && j > 0)
											sum += commulative[i-1][j-1][k2];
										if (i > 0 && k > 0)
											sum += commulative[i-1][j2][k-1];
										if (j > 0 && k > 0)
											sum += commulative[i2][j-1][k-1];

										if (i > 0 && j > 0 && k > 0)
											sum -= commulative[i-1][j-1][k-1];
										max = Math.max(max, sum);
									}
								}
							}
						}
					}
				}

				return max;
			}
		}
