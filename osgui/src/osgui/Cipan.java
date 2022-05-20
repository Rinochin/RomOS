package osgui;

import java.util.Scanner;
import java.util.Arrays;

public class Cipan {
	private int m;		//需要遍历的磁道(柱面)数量
	private int cidao[] = new int[m];	//存放需遍历的磁盘号的数组
//	public int[] cidao = { 55, 72, 100, 88, 93, 66 };
	private int now;	//当前磁头位于的磁道号
	private int choice = 0;		//选择磁头的移动方向
	public int Sum[] = new int[4];	//存储4种算法的磁头移动的总距离
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}	
	public int getNow() {
		return now;
	}
	public void setNow(int now) {
		this.now = now;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public void setCidao(int[] cidao) {
		this.cidao = cidao;
	}
	public int[] getCidao() {
		return cidao;
	}

	//1.先来先服务
	public String FCFS() // 磁道号数组，个数为m
	{
		int sum = 0; // 总寻道长度
		String s = "";
		sum += Math.abs(cidao[0] - now);// 求当前输入磁道和第一要访问磁道的距离
		System.out.print(now);
		for (int i = 0; i < m; i++) // 输出磁盘扫描序列
		{	
			s = s+" --> " + String.valueOf(cidao[i]);
			//System.out.print(" --> " + cidao[i]);
		}
		for (int i = 0; i < m - 1; i++) // 求总寻道长度
		{
			sum += Math.abs(cidao[i + 1] - cidao[i]);
		}
		Sum[0] = sum;
		System.out.println(Sum[0]);
		System.out.println();
		System.out.println("寻道长度：" + sum);
		return s;
	}
	
	//2.最短寻道时间优先
	public String SSTF() {
		int k = 1; // 用于第三种情况找到当前磁道号在排序好序列的位置
		int left = -1, right = -1;	//设置左右两个指针
		int i, j, sum = 0;
		String s = "";
		Arrays.sort(cidao); // 调用arrays工具类快速排序算法升序处理

		// 注：柱面的编号是从外到内从0开始编号的

		// 第一种种情况：若当前磁道号大于请求序列中最大者，则直接由内向外依次给予各请求服务
		if (cidao[m - 1] <= now) {//比如：排序后 55 66 72 88 93 100 若当前磁道大于100就直接从100开始予以各请求服务			
			for (i = m - 1; i >= 0; i--)
				s = s+" --> " + String.valueOf(cidao[i]);
				//System.out.print(" --> " + cidao[i]);
			sum = now - cidao[0];
		}
		
		// 第二种情况：若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务，与第一种同理
		if (cidao[0] >= now) {
			//System.out.println("磁盘扫描序列为：");
			System.out.print(now);
			for (i = 0; i < m; i++)
				//System.out.print(" --> " + cidao[i]);
				s = s+" --> " + String.valueOf(cidao[i]);
			sum = cidao[m - 1] - now;
		}

		// 第三种情况：若当前磁道号大于请求序列中最小者且小于最大者
		if (now > cidao[0] && now < cidao[m - 1]) {
			//System.out.println("磁盘扫描序列为：");

			while (cidao[k] < now) // 确定当前磁道在已排的序列中的位置
			{
				k++; // k从1开始找
						// 例如：55 66 72 88 93 100 若当前磁道号为90 直到找到93才会跳出循环，此时k=4
			}
			left = k - 1; // 表示此时位置的左边逻辑下标
			right = k; // 表示此时位置的右边逻辑下标

			while ((left >= 0) && (right < m)) // 当前磁道在请求序列范围内
			{ // 选择与当前磁道最近的请求给予服务
				if ((now - cidao[left]) <= (cidao[right] - now)) // 假如左边距离小于右边
				{ // 例如90和88比较在和93比较看谁距离短就先给哪个服务
					s = s+" --> " + String.valueOf(cidao[left]);
					//System.out.print(" --> " + cidao[left]); // 这是左边距离近的情况
					sum += now - cidao[left];
					now = cidao[left];
					left = left - 1;	//指针向左移动一位
				} else { // 假如右边边距离小于左边
					s = s+" --> " + String.valueOf(cidao[right]);
					//System.out.print(" --> " + cidao[right]); // 这是右边距离近的情况
					sum += cidao[right] - now;
					now = cidao[right];
					right = right + 1;	//指针向右移动一位
				}
			}

			if (left == -1) // 磁头移动到序列的最小号，返回内侧扫描仍未扫描的磁道
			{
				for (j = right; j < m; j++) {
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print("--> " + cidao[j]);
				}
				sum += cidao[m - 1] - cidao[0];
			} else // 磁头移动到序列的最大号，返回外侧扫描仍未扫描的磁道
			{
				for (j = left; j >= 0; j--) {
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print("--> " + cidao[j]);// 例如55 66 72 88 93 100假设当前磁道90
				} // 则按前面的算法会有90-->88-->93-->100,此时到100时right+1=6跳出while循环
				sum += cidao[m - 1] - cidao[0]; // 来到了else这个分支，把磁头转到之前left的位置循环输出
			} // sum直接等于最后一个减去第一个，因为他总是会扫描到最外侧的磁道号
		} // 和（100-72）+（72-66）+（66-55）= 100-55 = 45 是一样的
		Sum[1] = sum;
		return s;
	}
	
	//3.电梯算法
	public String SCAN() // 先要给出当前磁道号和移动臂的移动方向
	{
		int k = 1;
		int left = -1, right = -1;
		//int choice = -1;
		int i, j, sum = 0;
		String s = "";
		Arrays.sort(cidao); // 排序
		
		// 第一种情况：若当前磁道号大于请求序列中最大者，则直接由内向外依次给予各请求服务
		if (cidao[m - 1] <= now) {
			for (i = m - 1; i >= 0; i--)
				s = s+" --> " + String.valueOf(cidao[i]);
				//System.out.print(" --> " + cidao[i]);
			sum = now - cidao[0]; // 反正都要扫描到最外侧，直接减最小的那个得的距离和一个一个减在相加是等价的
		}

		// 第二种情况：若当前磁道号小于请求序列中最小者，则直接由外向内依次给予各请求服务
		if (cidao[0] >= now) {
			for (i = 0; i < m; i++)
				s = s+" --> " + String.valueOf(cidao[i]);
				//System.out.print(" --> " + cidao[i]);
			sum = cidao[m - 1] - now; // 同理
		}

		// 第三种情况：若当前磁道号大于请求序列中最小者且小于最大者--电梯算法
		if (now > cidao[0] && now < cidao[m - 1]) {
			while (cidao[k] < now) // 和SSTF算法一样先找到当前寻道号的位置
			{
				k++;
			}
			left = k - 1;
			right = k;
			//System.out.println("请输入当前移动臂的移动的方向 (1 表示向内 ，0表示向外) : ");
			if (choice == 0) // 选择移动臂方向向外，则先向外扫描
			{
				for (j = left; j >= 0; j--) // 往磁道号小的方向扫描，即向外扫描
				{
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				for (j = right; j < m; j++) // 磁头移动到最小号，则改变方向向内扫描未扫描的磁道
				{
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				sum = now - 2 * cidao[0] + cidao[m - 1]; // 拿55 66 72 88 93 100举例子，当前磁道号是90，
			} // 那么90先会往内扫描，扫描到最小号，然后在转回到了right=93处，又再一次经过了now,然后向右扫描到最大号
				// 所以可以这么算sum =(now-cidao[0])*2 + (cidao[m-1]-now)

			else // 选择移动臂方向向内，则先向内扫描
			{
				for (j = right; j < m; j++) {
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				for (j = left; j >= 0; j--) // 磁头移动到最大号，则改变方向向外扫描未扫描的磁道
				{
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				sum = -now - cidao[0] + 2 * cidao[m - 1]; // 同理sum =(cidao[m-1]-now)*2 + (now - cidao[0])
			}
		}
		Sum[2] = sum;
		return s;
	}
	
	//4.循环扫描
	public String CSCAN() // 这里默认扫描方向是先向磁道号变小的方向进行，即从内到外
	{
		int k = 1;
		int left = -1, right = -1;
		int i, j, sum = 0;
		String s = "";
		Arrays.sort(cidao);

		// 第一种情况：若当前磁道号大于请求序列中最大者，则直接将移动臂移动到最小号磁道依次向内给予各请求服务
		if (cidao[m - 1] <= now) {
			for (i = m - 1; i >= 0; i--)
				s = s+" --> " + String.valueOf(cidao[i]);
				//System.out.print(" --> " + cidao[i]);
			sum = now - cidao[0]; // 和SCAN算法一样 55 66 72 88 93 100 当前磁道号为101
		}

		// 第二种情况：若当前磁道号小于请求序列中最小者，则移动磁道到最大位置由外向内依次给予各请求服务,
		if (cidao[0] >= now) {
			for (i = m - 1; i >= 0; i--)
				s = s+" --> " + String.valueOf(cidao[i]);
				//System.out.print(" --> " + cidao[i]);
			sum = 2 * cidao[m - 1] - now - cidao[0]; // 55 66 72 88 93 100 当前磁道号54
		} // sum = (cidao[m-1] - now) + (cidao[m-1]-cidao[0])

		// 第三种情况：若当前磁道号大于请求序列中最小者且小于最大者
		if (now > cidao[0] && now < cidao[m - 1]) {
			while (cidao[k] < now) // 同上，先找到当前磁道号的位置
			{
				k++;
			}
			left = k - 1;
			right = k;
			
			//默认向小
//			for (j = left; j >= 0; j--) // 因为是默认先扫描最小磁道号在移到最大磁道号扫描
//			{ // 例：55 66 72 88 93 100 当前磁道号90
//				s = s+" --> " + String.valueOf(cidao[j]);
//				//System.out.print(" --> " + cidao[j]);
//			}
//			for (j = m - 1; j > left; j--) // 当扫描完最小号磁道，磁头直接移动到最大号磁道，再向外扫描未扫描的磁道
//			{
//				s = s+" --> " + String.valueOf(cidao[j]);
//				//System.out.print(" --> " + cidao[j]);
//			}
//			sum = 2 * cidao[m - 1] - cidao[right] + now - 2 * cidao[0];
//			// sum=(now-cidao[0])+(cidao[m-1]-cidao[0])+(cidao[m-1]-cidao[right])
			
			if (choice == 0) // 选择移动臂方向向外，则先向外扫描
			{
				for (j = left; j >= 0; j--) // 往磁道号小的方向扫描，即向外扫描
				{
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				for (j = m-1; j > left; j--) // 磁头移动到最小号，则改变方向向内扫描未扫描的磁道
				{
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				sum = 2 * cidao[m - 1] - cidao[right] + now - 2 * cidao[0];
			} 

			else // 选择移动臂方向向内，则先向内扫描
			{
				for (j = right; j < m; j++) {
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				for (j = 0; j < right; j++) // 磁头移动到最大号，扫描未扫描的磁道
				{
					s = s+" --> " + String.valueOf(cidao[j]);
					//System.out.print(" --> " + cidao[j]);
				}
				sum = 2 * cidao[m - 1] + cidao[left] - now - 2 * cidao[0];
			}
		}
		Sum[3] = sum;
		return s;
	}
}

