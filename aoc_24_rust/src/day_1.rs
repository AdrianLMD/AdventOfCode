use std::fs::File;
use std::io::{self, BufRead, BufReader};

fn parse_file(path: &str) -> io::Result<(Vec<i32>, Vec<i32>)> {
    let file = File::open(path)?;
    let reader = BufReader::new(file);
    let mut left_vec = Vec::new();
    let mut right_vec = Vec::new();

    for line in reader.lines() {
        let line = line?;
        let parts: Vec<&str> = line.split('#').collect();
        if parts.len() == 2 {
            let left: i32 = parts[0].parse().expect("left side not parsable");
            let right: i32 = parts[1].parse().expect("right side not parsable");
            left_vec.push(left);
            right_vec.push(right);
        }
    }

    left_vec.sort();
    right_vec.sort();
    println!("test");
    Ok((left_vec, right_vec))
}

pub fn day_1_controller(path: &str) -> (i32, i32){
    let (left_vec, right_vec) = match parse_file(path) {
        Ok((l, r)) => (l, r),
        Err(e) => {
            eprintln!("Error parsing: {}", e);
            return (-1, -1)
        }
    };
    let output_1 = distance(&left_vec, &right_vec);
    let output_2 = similarity(&left_vec, &right_vec);
    (output_1, output_2)
}

pub fn distance(left_vec: &Vec<i32>, right_vec: &Vec<i32>) -> i32 {
    

    let mut total_distance = 0;

    if left_vec.len() == right_vec.len() {
        for i in 0..left_vec.len() {
            total_distance += (left_vec[i]-right_vec[i]).abs()
        }
    } else {
        total_distance = 404;
    }
    total_distance
}

pub fn similarity(left_vec: &Vec<i32>, right_vec: &Vec<i32>) -> i32 {

    let mut count: i32 = 0;
    let mut sim_score: i32 = 0;

    for i in 0..left_vec.len() {
        for j in 0..right_vec.len() {
            if left_vec[i] == right_vec[j] {
                count += 1;
            }
        }
        sim_score += left_vec[i] * count;
        count = 0;
    }
    sim_score

}