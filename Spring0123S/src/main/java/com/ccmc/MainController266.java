package com.ccmc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;


@Controller
public class MainController266 {
	@GetMapping("/V2/{month}")
	public String getCalendarV2(@PathVariable int month, Model model) {
		model.addAttribute("controller", new Object() {
		}.getClass().getEnclosingClass().getName());
		Student student = new Student();
		student.id = "C180266"; // your id
		student.name = "TRAN TRONG HOAI"; // your name
		student.birthday_m = 7; // your birthday month
		student.birthday_d = 28; // your birthday day
//test github
		model.addAttribute("id", student.id);
		model.addAttribute("name", student.name);
		model.addAttribute("birthday", student.birthday_m + "/" + student.birthday_d);

//2020年 月の最終日の配列 lastDay
		int[] lastDay = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
//2020年 月の開始曜日の配列(0:SUN, 1:MON, ... , 6:SAT) startWeek
		int[] startWeek = { 3, 6, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2 };
//カレンダーの配列値 変数dataに格納する
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] monthOfYear = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
				"OCTOBER", "NOVEMBER", "DECEMBER" };
		String[] row = new String[7];
		int j = startWeek[month - 1];

		for (int ii = 1; ii <= lastDay[month - 1]; ii++) {
			row[j] = String.valueOf(ii);

			if (month == student.birthday_m && ii == student.birthday_d)
				row[j] = "<span style='color:red'>" + String.valueOf(ii) + "*" + "</span>";

			if ((((ii) + startWeek[month - 1]) % 7 == 0) || ii == lastDay[month - 1]) {
				data.add(row.clone());
				row = new String[7];

			}

			j++;
			if (j > 6)
				j = 0;

		}

		model.addAttribute("data", data);
		model.addAttribute("month", month + "[" + monthOfYear[month - 1] + "]" + " 2020"); // 問2
		return "CalV2"; // CalV2.html
	}

	@GetMapping("/V3/{year}/{month}")
	public String getCalendarV3(@PathVariable int year, @PathVariable int month, Model model) {
		model.addAttribute("controller", new Object() {
		}.getClass().getEnclosingClass().getName());
		Student student = new Student();
		student.id = "C180266"; // your id
		student.name = "TRAN TRONG HOAI"; // your name
		student.birthday_m = 7; // your birthday month
		student.birthday_d = 28; // your birthday day

		model.addAttribute("id", student.id);
		model.addAttribute("name", student.name);
		model.addAttribute("birthday", student.birthday_m + "/" + student.birthday_d);

		// 2020年 月の最終日の配列 lastDay
		int[] lastDay = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		// 2020年 月の開始曜日の配列(0:SUN, 1:MON, ... , 6:SAT) startWeek
		int[] startWeek = { 3, 6, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2 };
		// カレンダーの配列値 変数dataに格納する
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] monthOfYear = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
				"OCTOBER", "NOVEMBER", "DECEMBER" };
		String[] row = new String[7];

		int[] plusyear = new int[12];

		if (year < 2020) {
			for (int plusyears : plusyear) {
				int timeyear = (2020 - year) / 4;
				if ((year % 4 == 0) && (year % 100 != 0) || ((year % 400 == 0))) {
					plusyear[0] = 2020 - year + timeyear;
					plusyear[1] = 2020 - year + timeyear;
					for (int x = 2; x < plusyear.length; x++) {
						plusyear[x] = 2020 - year + timeyear;
					}
				}
				if ((year % 4 == 1) && (year % 100 != 0) || ((year % 400 == 0))) {
					plusyear[0] = 2020 - year + 1 + timeyear;
					plusyear[1] = 2020 - year + 1 + timeyear;
					for (int x = 2; x < plusyear.length; x++) {
						plusyear[x] = 2020 - year + timeyear;
					}
				}

				else {

					plusyear[0] = 2020 - year + timeyear;
					plusyear[1] = 2020 - year + timeyear;
					for (int x = 2; x < plusyear.length; x++) {
						plusyear[x] = 2020 - year + 1 + timeyear;
					}
				}

			}

			for (int i = 0; i < startWeek.length; i++) {
				startWeek[i] -= plusyear[i];
				if (startWeek[i] > 6)
					startWeek[i] = startWeek[i] % 7;
				if (startWeek[i] < 0)
					startWeek[i] = (startWeek[i] % 7) + 7;
			}
		}

		if (year > 2020) {
			int timeyear = (2020 - year) / 4;
			for (int plusyears : plusyear) {
				if ((year % 4 == 0) && (year % 100 != 0) || ((year % 400 == 0))) {
					plusyear[0] = year - 2020 + timeyear;
					plusyear[1] = year - 2020 + timeyear;
					for (int x = 2; x < plusyear.length; x++) {
						plusyear[x] = year - 2020 + timeyear;
					}
				}
				if ((year % 4 == 1) && (year % 100 != 0) || ((year % 400 == 0))) {
					plusyear[0] = year - 2020 + 1 + timeyear;
					plusyear[1] = year - 2020 + 1 + timeyear;
					for (int x = 2; x < plusyear.length; x++) {
						plusyear[x] = year - 2020 + timeyear;
					}
				}

				else {

					plusyear[0] = year - 2020;
					plusyear[1] = year - 2020;
					for (int x = 2; x < plusyear.length; x++) {
						plusyear[x] = year - 2020 + 1 + timeyear;
					}
				}

			}

			for (int i = 0; i < startWeek.length; i++) {
				startWeek[i] += plusyear[i];
				if (startWeek[i] > 6)
					startWeek[i] = startWeek[i] % 7;
				if (startWeek[i] < 0)
					startWeek[i] = (startWeek[i] % 7) + 7;
			}
		}
		
		
		
		int j = startWeek[month - 1];

		for (int ii = 1; ii <= lastDay[month - 1]; ii++) {
			if ((year % 4 == 0) && (year % 100 != 0) || ((year % 400 == 0) && month == 2)) {
				lastDay[1] = 29;
			} else {
				lastDay[1] = 28;
			}

			row[j] = String.valueOf(ii);

			if (month == student.birthday_m && ii == student.birthday_d)
				row[j] = "<span style='color:red'>" + String.valueOf(ii) + "*" + "</span>";

			if ((((ii) + startWeek[month - 1]) % 7 == 0) || ii == lastDay[month - 1]) {
				data.add(row.clone());
				row = new String[7];

			}

			j++;
			if (j > 6)
				j = 0;
		}

		model.addAttribute("data", data);
		model.addAttribute("year", year);
		model.addAttribute("month", month + "[" + monthOfYear[month - 1] + "] "); // 問2

		return "CalV3"; // CalV3.html
	}
}