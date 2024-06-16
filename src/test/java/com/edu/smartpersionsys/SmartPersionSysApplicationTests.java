package com.edu.smartpersionsys;

import com.edu.smartpersionsys.mapper.DishMapper;
import com.edu.smartpersionsys.mapper.DishScoreMapper;
import com.edu.smartpersionsys.mapper.SymptomRecordMapper;
import com.edu.smartpersionsys.mapper.TaskMapper;
import com.edu.smartpersionsys.pojo.Dish;
import com.edu.smartpersionsys.pojo.DishScore;
import com.edu.smartpersionsys.pojo.Symptoms;
import com.edu.smartpersionsys.pojo.Task;
import com.edu.smartpersionsys.service.OlderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SmartPersionSysApplicationTests {


	@Autowired
	private SymptomRecordMapper symptomRecordMapper;

	@Autowired
	private OlderService olderService;

	@Test
	void contextLoads() {
	}



	@Test
	void getSymptomNameAndCountByDateTest(){
		Date date = new Date();
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(date);
		List<Symptoms> symptoms = symptomRecordMapper.getSymptomNameAndCountByDate(format);
	}

	@Test
	void getIncrease(){
		olderService.getIncrease(7);
	}

	@Autowired
	private TaskMapper taskMapper;
	@Test
	void getTasks(){
		List<Task> all = taskMapper.findAll();
		all.forEach(System.out::println);
	}

//	@Autowired
//	private DishMapper dishMapper;
//	@Autowired
//	private DishScoreMapper dishScoreMapper;
//	@Test
//	void cf(){
//		//1.获取用户对菜品的评分数据
//		List<DishScore> dishScores = dishScoreMapper.findAll();
//		//获取所有的菜品
//		List<Dish> dishs = dishMapper.findAll();
//		int size = dishs.size();
//			//构建一个二维数组，
//		/**
//		 [
//		 	id:[score1,0,score2],
//		 	[],
//		 ]
//		 */
//		//行表示对用户某样菜品的评分，其中菜品的id表示列
//		// olderid: score1,score2;
//		List<HashMap<Integer,List<Integer>>> userScores = new ArrayList<>();
//		//构建用户菜品矩阵
//
//		int prep = 0;//前一个用户的id
//		int index = 0;
//		HashMap<Integer,List<Integer>> rowScore = new HashMap<>(); //作为一个用户的评分数据
//		List<Integer> scores = new ArrayList<>(Collections.nCopies(size, 0)); // 分数表设置默认值为零
//
//		for(DishScore dishScore:dishScores){
//			//初始设置
//			if(prep == 0){
//				//设置当前用户的评分集
//				prep = dishScore.getOlderId();
//				rowScore.put(prep,scores);
//				index = dishScore.getDishId()-1;
//				scores.set(index,dishScore.getScore());
//			}
//			//判断是否是同一个用户
//			else if (dishScore.getOlderId() != prep){ //不同用户
//				//将上一个用户的所有数据添加到用户菜品矩阵中
//
//				rowScore.replace(prep,scores);
//				userScores.add(rowScore);
//
//				//清除rowScore、scores数据
//				rowScore = new HashMap<>();
//				scores = new ArrayList<>(Collections.nCopies(size, 0));
//				//设置当前用户的评分集
//				prep = dishScore.getOlderId();
//				rowScore.put(prep,scores);
//
//				index = dishScore.getDishId()-1;
//				scores.set(index,dishScore.getScore());
//			}else{//相同的用户
//				//导入分数
//				index = dishScore.getDishId()-1;
//				scores.set(index,dishScore.getScore());
//			}
//		}
//		//最后一个还没添加
//		rowScore.replace(prep,scores);
//		userScores.add(rowScore);
//		System.out.println("-------------用户-菜品表-------------");
//		userScores.forEach(System.out::println);
//		//计算每个用户的打分均值
//		List<Double> userAvg = new ArrayList<>(userScores.size());
//		System.out.println("userScores.size():"+userAvg);
//		int avg_j = 1;
//
//		for (HashMap<Integer,List<Integer>> obj : userScores){
//			double avg = 0;
//			int avg_count = 0;
//			for (int i=0;i<obj.get(avg_j).size();i++){
//				double value = obj.get(avg_j).get(i);
//				if(value!=0){
//					avg += value;
//					avg_count++;
//				}
//			}
//			avg_j++;
//			//添加每个用户的评分均值 0 ~ 用户数-1
//			userAvg.add(avg/avg_count);
//		}
//		System.out.println(userAvg);
//
//
//		//2.制作菜品用户倒排表；
//		System.out.println("制作菜品用户倒排表；");
//
//		// 转置后的数据结构
//		Map<Integer, List<Integer>> transposedScores = new HashMap<>();
//
//		for (Map<Integer, List<Integer>> userScore : userScores) {
//			for (Map.Entry<Integer, List<Integer>> entry : userScore.entrySet()) {
//				int userId = entry.getKey();
//				List<Integer> scores1 = entry.getValue();
//
//				for (int i = 0; i < scores1.size(); i++) {
//					if (transposedScores.containsKey(i + 1)) {
//						transposedScores.get(i + 1).add(scores1.get(i));
//					} else {
//						List<Integer> newList = new ArrayList<>();
//						newList.add(scores1.get(i));
//						transposedScores.put(i + 1, newList);
//					}
//				}
//			}
//		}
//
//		// 打印转置后的数据结构
//		for (Map.Entry<Integer, List<Integer>> entry : transposedScores.entrySet()) {
//			System.out.print("{" + entry.getKey() + "=[");
//			for (int i = 0; i < entry.getValue().size(); i++) {
//				System.out.print(entry.getValue().get(i));
//				if (i < entry.getValue().size() - 1) {
//					System.out.print(", ");
//				}
//			}
//			System.out.println("]}");
//		}
//
//
//
//		//3.计算菜品之间的相似度，构建相似度矩阵
//		//定义及初始化相似度矩阵 n+1*n+1；
//		Map<Integer,List<Double>> similarityMatrix = new HashMap<>(size+1);
//		for(int i=0;i<size+1;i++){
//			similarityMatrix.put(i+1,new ArrayList<Double>());
//			for (int j=0;j<size+1;j++){
//				similarityMatrix.get(i+1).add(0.0);
//			}
//		}
//// 计算余弦相似度
//		for (int i = 1; i <= transposedScores.size(); i++) {
//			List<Integer> itemScores1 = transposedScores.get(i);
//			for (int j = i + 1; j <= transposedScores.size(); j++) {
//				List<Integer> itemScores2 = transposedScores.get(j);
//				double dotProduct = 0.0;
//				int norm1 = 0;
//				int norm2 = 0;
//				for (int k = 0; k < itemScores1.size(); k++) {
//					double score1=0,score2=0;
//					if (itemScores1.get(k)>0){
//						score1=itemScores1.get(k)-userAvg.get(k); //这里使用的是更正的余弦相似度
////						score1=1; //这里使用的是二分法处理处理数据，
//					}
//					if (itemScores2.get(k)>0){
//						score2=itemScores2.get(k)-userAvg.get(k);
////						score2=1;
//					}
//					norm1++;
//					norm2++;
//					dotProduct += score1 * score2;
//				}
//				//保留小数点后两位
//				NumberFormat nf = NumberFormat.getNumberInstance();
//				nf.setMaximumFractionDigits(2);
//				// 如果不需要四舍五入，可以使用RoundingMode.DOWN
////				nf.setRoundingMode(RoundingMode.UP);
//				double cosineSimilarity = dotProduct / (Math.sqrt(norm1*norm2));
//				cosineSimilarity = Math.round(cosineSimilarity * 100.0) / 100.0;
//				System.out.println("Cosine Similarity between Item " + i + " and Item " + j + ": " + nf.format(cosineSimilarity));
//				//设置相似度矩阵
//				similarityMatrix.get(i).set(j,cosineSimilarity);
//				similarityMatrix.get(j).set(i,cosineSimilarity);
//			}
//		}
//
//		System.out.println("显示物品-物品的相似度矩阵，其中行列分别表示物品在数据库中的id");
//		for(Map.Entry<Integer,List<Double>> entry:similarityMatrix.entrySet()){
//			System.out.print("{"+entry.getKey() + "=[");
//			for (int i=0;i<entry.getValue().size(); i++){
//				System.out.print(entry.getValue().get(i));
//				if(i<entry.getValue().size() - 1){
//					System.out.print(", ");
//				}
//			}
//			System.out.println("]}");
//		}
//
//
//		//4.生成推荐列表
//		System.out.println("给用户4推荐食物：");
//			//获取用户1对所有食物的点评记录
//		List<Integer> firstUser = userScores.get(3).get(4);
//			//获取用户还没有评分的菜品；对菜品进行循环，计算出其他菜品与这个菜品的加权相似度；最终将数据存到链表表中；
//        Map<Integer,Double> res = new HashMap<>();
//		for(int i=0;i<firstUser.size();i++){
//			//未评价的菜品 i
//			if(firstUser.get(i) != 0){
//				continue;
//			}
//			double sum=0;
//			//计算出其他菜品与这个菜品的加权相似度评分
//			for (int j=0;j<firstUser.size();j++){
//				//参数：老人对菜品的评分：firstUser[j], 菜品与推荐的菜品之间的相似度  similarityMatrix.get(i+1).get(j+1)
//				//1=[5, 5, 0, 4, 0]
//				//物品i与其他物品的相似度=[0.0, 0.29, 0.26, 0.0, 0.33, 0.67]
//				double v = firstUser.get(j) * similarityMatrix.get(i + 1).get(j + 1);
//				sum += v;
//			}
//            res.put(i+1,sum);
//			System.out.println(i+1 +":"+sum);
//		}
//        //推荐列表
//        // 升序比较器
//        Comparator<Map.Entry<Integer, Double>> valueComparator = new Comparator<Map.Entry<Integer, Double>>() {
//			@Override
//			public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
//				return Double.compare(o2.getValue(), o1.getValue());
//			}
//        };
//        // map转换成list进行排序
//        List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer, Double>>(res.entrySet());
//        // 排序
//        Collections.sort(list,valueComparator);
//
//        // 默认情况下，TreeMap对key进行升序排序
//        System.out.println("------------map按照value降序排序--------------------");
//        for (Map.Entry<Integer, Double> entry : list) {
//            System.out.println("菜品id："+entry.getKey() + ", 推荐分数：" + entry.getValue()+", 推荐食物：" + dishs.get(entry.getKey() - 1));
//        }
//        System.out.println(list);
//    }

	@Autowired
	private DishMapper dishMapper;
	@Autowired
	private DishScoreMapper dishScoreMapper;

	@Test
	void cf() {
		// 1. 获取用户对菜品的评分数据
		List<DishScore> dishScores = dishScoreMapper.findAll();
		// 获取所有的菜品
		List<Dish> dishs = dishMapper.findAll();
		int size = dishs.size();

		// 构建用户菜品评分矩阵
		List<HashMap<Integer, List<Integer>>> userScores = new ArrayList<>();
		int prep = 0; // 前一个用户的id
		HashMap<Integer, List<Integer>> rowScore = new HashMap<>(); // 作为一个用户的评分数据
		List<Integer> scores = new ArrayList<>(Collections.nCopies(size, 0)); // 分数表设置默认值为零

		for (DishScore dishScore : dishScores) {
			if (prep == 0) {
				// 设置当前用户的评分集
				prep = dishScore.getOlderId();
				rowScore.put(prep, scores);
				scores.set(dishScore.getDishId() - 1, dishScore.getScore());
			} else if (dishScore.getOlderId() != prep) { // 不同用户
				// 将上一个用户的所有数据添加到用户菜品矩阵中
				userScores.add(new HashMap<>(rowScore));
				rowScore.clear();
				scores = new ArrayList<>(Collections.nCopies(size, 0));
				// 设置当前用户的评分集
				prep = dishScore.getOlderId();
				rowScore.put(prep, scores);
				scores.set(dishScore.getDishId() - 1, dishScore.getScore());
			} else { // 相同的用户
				// 导入分数
				scores.set(dishScore.getDishId() - 1, dishScore.getScore());
			}
		}
		// 最后一个用户的评分数据还未添加
		userScores.add(new HashMap<>(rowScore));

		System.out.println("-------------用户-菜品表-------------");
		userScores.forEach(System.out::println);

		// 计算每个用户的打分均值
		List<Double> userAvg = new ArrayList<>();
		for (HashMap<Integer, List<Integer>> obj : userScores) {
			double sum = 0;
			int count = 0;
			for (Map.Entry<Integer, List<Integer>> entry : obj.entrySet()) {
				for (int score : entry.getValue()) {
					if (score != 0) {
						sum += score;
						count++;
					}
				}
			}
			userAvg.add(sum / count);
		}
		System.out.println(userAvg);

		// 2. 制作菜品用户倒排表
		System.out.println("制作菜品用户倒排表；");
		Map<Integer, List<Integer>> transposedScores = new HashMap<>();
		for (int i = 0; i < size; i++) {
			transposedScores.put(i + 1, new ArrayList<>());
		}

		for (HashMap<Integer, List<Integer>> userScore : userScores) {
			for (Map.Entry<Integer, List<Integer>> entry : userScore.entrySet()) {
				List<Integer> scores1 = entry.getValue();
				for (int i = 0; i < scores1.size(); i++) {
					transposedScores.get(i + 1).add(scores1.get(i));
				}
			}
		}

		// 打印转置后的数据结构
		for (Map.Entry<Integer, List<Integer>> entry : transposedScores.entrySet()) {
			System.out.print("{" + entry.getKey() + "=[");
			for (int i = 0; i < entry.getValue().size(); i++) {
				System.out.print(entry.getValue().get(i));
				if (i < entry.getValue().size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]}");
		}

		// 3. 计算菜品之间的相似度，构建相似度矩阵
		Map<Integer, List<Double>> similarityMatrix = new HashMap<>();
		for (int i = 1; i <= size; i++) {
			similarityMatrix.put(i, new ArrayList<>(Collections.nCopies(size + 1, 0.0)));
		}

		// 计算余弦相似度
		for (int i = 1; i <= transposedScores.size(); i++) {
			List<Integer> itemScores1 = transposedScores.get(i);
			for (int j = i + 1; j <= transposedScores.size(); j++) {
				List<Integer> itemScores2 = transposedScores.get(j);
				double dotProduct = 0.0;
				double norm1 = 0.0;
				double norm2 = 0.0;
				for (int k = 0; k < itemScores1.size(); k++) {
					double score1 = 0, score2 = 0;
					if (itemScores1.get(k) > 0) {
						score1 = itemScores1.get(k) - userAvg.get(k);
					}
					if (itemScores2.get(k) > 0) {
						score2 = itemScores2.get(k) - userAvg.get(k);
					}
					dotProduct += score1 * score2;
					norm1 += score1 * score1;
					norm2 += score2 * score2;
				}
				double cosineSimilarity = (norm1 == 0 || norm2 == 0) ? 0.0 : dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
//				cosineSimilarity = Math.round(cosineSimilarity * 100.0) / 100.0;
				System.out.println("Cosine Similarity between Item " + i + " and Item " + j + ": " + cosineSimilarity);
				similarityMatrix.get(i).set(j, cosineSimilarity);
				similarityMatrix.get(j).set(i, cosineSimilarity);
			}
		}

		System.out.println("显示物品-物品的相似度矩阵，其中行列分别表示物品在数据库中的id");
		for (Map.Entry<Integer, List<Double>> entry : similarityMatrix.entrySet()) {
			System.out.print("{" + entry.getKey() + "=[");
			for (int i = 0; i < entry.getValue().size(); i++) {
				System.out.print(entry.getValue().get(i));
				if (i < entry.getValue().size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]}");
		}

		// 4. 生成推荐列表
		System.out.println("给用户4推荐食物：");
		// 获取用户4对所有食物的点评记录
		List<Integer> fourthUser = userScores.get(19).get(20);
		Map<Integer, Double> res = new HashMap<>();
		for (int i = 0; i < fourthUser.size(); i++) {
			if (fourthUser.get(i) != 0) continue;
			double sum = 0;
			double sumSim = 0;
			for (int j = 0; j < fourthUser.size(); j++) {
				if (fourthUser.get(j) != 0) {
					double similarity = similarityMatrix.get(i + 1).get(j + 1);
					sum += similarity * fourthUser.get(j);
					sumSim += Math.abs(similarity);
				}
			}
			double weightedScore = sumSim == 0 ? 0 : sum / sumSim;
			res.put(i + 1, weightedScore);
		}

		// 推荐列表
		List<Map.Entry<Integer, Double>> list = new ArrayList<>(res.entrySet());
		list.sort(Map.Entry.<Integer, Double>comparingByValue().reversed());
		System.out.println("------------推荐列表--------------------");
		for (Map.Entry<Integer, Double> entry : list) {
			System.out.println("菜品id：" + entry.getKey() + ", 推荐分数：" + entry.getValue() + ", 推荐食物：" + dishs.get(entry.getKey() - 1));
		}
	}

}
