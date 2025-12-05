use std::fs::File;
use std::io::{BufRead, BufReader};
use regex::Regex;

fn parse_file(path: &str) -> std::io::Result<String>{
    let file = File::open(path)?;
    let mut reader = BufReader::new(file);
    
    let mut line: String = String::new();
    reader.read_line(&mut line)?;

    Ok(line)
}

pub fn day_3_controller(path: &str) -> (i32, i32) {
    let line:String = parse_file(path).expect("error parsing file");
    logic(&line)
}

fn logic(line: &str) -> (i32, i32){

    let mut sum: i32 = 0;
    let mut sum_total = 0;
    let mut active:bool = true;
    let re = Regex::new(r"don't\(\)|do\(\)|mul\((\d+),(\d+)\)").unwrap();
    for caps in re.captures_iter(line) {
        if&caps[0] == "don't()" {
            active = false;
        } else if &caps[0] == "do()" {
            active = true;
        } else if caps.get(1).is_some() && caps.get(2).is_some() {
            if active {
                let x: i32 = caps[1].parse().unwrap();
                let y: i32 = caps[2].parse().unwrap();
                sum+= x*y;
            } else {
                let x: i32 = caps[1].parse().unwrap();
                let y: i32 = caps[2].parse().unwrap();
                sum_total += x*y;
            }
        }
    }
    (sum_total, sum)
}