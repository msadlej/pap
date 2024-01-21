#!/bin/bash

JAR_FILE=LibMan.jar
DB_FILE=database/appDB.db
SQL_SCRIPT=database/sql/create_table.sql

EXTRACT_DIR=./tmp
INSTALL_DIR=/opt/LibMan
mkdir -p $INSTALL_DIR

# Function to print a progress bar
print_progress() {
    local width=50
    local percent=$1
    local progress=$((width * percent / 100))

    printf "["
    for ((i=0; i<width; i++)); do
        if ((i < progress)); then
            printf "="
        else
            printf " "
        fi
    done
    printf "] %d%%\r" "$percent"
}

# Function to log the current process
log_process() {
    echo "$1"
}

# Updating apt-get
log_process "Updating apt-get ..."
apt-get update > /dev/null 2>&1 &
pid_update=$!
while kill -0 $pid_update 2> /dev/null; do
    print_progress 10
    sleep 0.5
done
print_progress 10
echo

# Installing OpenJDK 8
log_process "Installing OpenJDK 8 ..."
apt-get install -y openjdk-8-jdk > /dev/null 2>&1 &
pid_java=$!
while kill -0 $pid_java 2> /dev/null; do
    print_progress 50
    sleep 0.5
done
print_progress 50
echo "Java version: "
java -version

# Installing sqlite3
log_process "Installing sqlite3 ..."
apt-get install -y sqlite3 > /dev/null 2>&1 &
pid_sqlite=$!
while kill -0 $pid_sqlite 2> /dev/null; do
    print_progress 90
    sleep 0.5
done
print_progress 90
echo "Sqlite3 version: "
sqlite3 --version

# Final progress bar with 100%
print_progress 100
echo

echo "Creating application installation directory ..."
cp ./$JAR_FILE $INSTALL_DIR
# Extract jar file
cd $INSTALL_DIR
echo "Extracting jar file ..."
jar xvf ./$JAR_FILE > /dev/null 2>&1
echo "Extracted jar file."
# Initialize database
echo "Initializing database ..."
# Modify write permissions
chmod 777 $INSTALL_DIR/$DB_FILE
sqlite3 $INSTALL_DIR/$DB_FILE < $INSTALL_DIR/$SQL_SCRIPT

echo "[Desktop Entry]
Name = Library Management
Exec = java -jar $INSTALL_DIR/LibMan.jar
Type = Application
Categories = Utility;" > /usr/share/applications/LibMan.desktop

# Provide executable permissions
chmod +x $INSTALL_DIR/LibMan.jar
chown -R "$USER:$USER" $INSTALL_DIR
echo "Installation complete."