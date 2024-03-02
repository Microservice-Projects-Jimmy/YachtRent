#!/bin/bash

# Specify the path to the root of your Spring Boot project
project_root="/home/jemsit/Desktop/gitlab/YachtRent/backend"

# Specify the path to each Spring Boot module
module1_path="$project_root/ApiGateway"
module2_path="$project_root/UserService"
module3_path="$project_root/YachtService"
# Add more module paths as needed

# Function to run a Spring Boot module
run_module() {
  cd "$1"
  mvn spring-boot:run
}

# Change the working directory to the project root
cd "$project_root"

# Run each module
run_module "$module1_path" &
run_module "$module2_path" &
run_module "$module3_path" &
# Add more run_module lines for additional modules

# Wait for all background processes to finish
wait
