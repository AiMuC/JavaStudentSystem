package com.aimuc.pojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class test {
	public static void main(String[] args) {
		// ȥ���ظ�ѧ�ŵ�����
		// HashSet hSet = new HashSet();
		// Student s1 = new Student("2006119202", "35012417501406051X",
		// "123456", "��", "��˹", 18);
		// Student s2 = new Student("2006119203", "35012417501406051X",
		// "123456", "��", "��˹", 18);
		// hSet.add(s1);
		// hSet.add(s2);
		// System.out.println(hSet);

		// ȥ���ظ�ѧ�Ų��Ұ���ѧ������
		// TreeSet tSet = new TreeSet();
		// tSet.add(new Student("2006119202", "35012417501406051X", "123456",
		// "��", "��˹", 18));
		// tSet.add(new Student("2006119201", "35012417501406051X", "123456",
		// "��", "����", 18));
		// Iterator iterator =tSet.iterator();
		// while (iterator.hasNext()) {
		// System.out.println(iterator.next());
		// }

		// HashMap
		Map map = new HashMap();
		map.put(1, new Student("2006119202", "35012417501406051X", "123456", "��", "��˹", 18));
		map.put(2, new Student("2006118202", "35012417501406051X", "123456", "��", "ɵ��", 18));
//		Collection<Student> students = map.values();
//		Iterator<Student> iterator = students.iterator();
//		while (iterator.hasNext()) {
//			Student student = iterator.next();
//			System.out.println(student);
//		}
		 Set keyset = map.keySet();
		 Iterator iterator = keyset.iterator();
		 while(iterator.hasNext()){
		 System.out.println("Value:"+map.get(iterator.next()));
		 }

		// HashMap
		// Map map = new HashMap();
		// map.put(1, new Student("2006119202", "35012417501406051X", "123456",
		// "��", "��˹", 18));
		// map.put(2, new Student("2006118202", "35012417501406051X", "123456",
		// "��", "ɵ��", 18));
		// Set entryset = map.entrySet();
		// Iterator iterator = entryset.iterator();
		// while(iterator.hasNext()){
		// Map.Entry entry = (Map.Entry) (iterator.next());
		// System.out.print(entry.getKey());
		// System.out.println(entry.getValue());
		// }
	}

}
