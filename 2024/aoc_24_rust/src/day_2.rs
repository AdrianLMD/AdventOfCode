use std::fs::File;
use std::io::{BufRead, BufReader};

fn parse_file(path: &str) -> std::io::Result<Vec<Vec<i32>>>{
    let file = File::open(path)?;
    let reader = BufReader::new(file);
    let mut reports: Vec<Vec<i32>> = Vec::new();

    for line in reader.lines() {
        let line = line?;
        let parts: Vec<i32> = line
        .split_whitespace()
        .map(|s| s.parse::<i32>()
        .expect("not parsable"))
        .collect();
        reports.push(parts);
    }

    Ok(reports)
}

pub fn day_2_controller(path: &str) -> (i32,i32){
    let reports = parse_file(path)
    .expect("Error parsing file");
    let mut count_safe: i32 = 0;
    let mut count_safe_dampened: i32 = 0;
    for i in 0..reports.len(){
        if increasing(&reports[i]) || decreasing(&reports[i]) {
            count_safe += 1;
        }
        if is_safe_dampened(&reports[i]) {
            count_safe_dampened += 1;
        }
    }
    (count_safe, count_safe_dampened)
}

fn is_safe_dampened(report: &Vec<i32>) -> bool{
    let mut report_cut = report.clone();
    for i in 0..report.len() {
        report_cut.remove(i);
        if increasing(&report_cut) || decreasing(&report_cut) {
            return true;
        }
        report_cut = report.clone();
        
    }
    false
}

fn increasing (report:&Vec<i32>) -> bool {
    for i in 0..report.len()-1 {
        if report [i] < report [i+1] || 
        (report[i]- report[i+1]).abs() > 3 || 
        (report[i]- report[i+1]).abs() < 1 {
            return false;
        }
    }
    true
}

fn decreasing (report:&Vec<i32>) -> bool {
    for i in 0..report.len()-1 {
        if report [i] > report [i+1] ||
         (report[i]- report[i+1]).abs() > 3 ||
         (report[i]- report[i+1]).abs() < 1 {
            return false;
        }
    }
    true
}