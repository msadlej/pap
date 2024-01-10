#!/bin/bash

INSTALL_DIR=/opt/app
mkdir -p $INSTALL_DIR

# Install Java
echo "Installing Java ..."
apt-get update
apt-get install -y openjdk-8-jdk
echo "Java version: "
java -version

#install sqlite3
echo "Installing sqlite3 ..."
apt-get install -y sqlite3
echo "Sqlite3 version: "
sqlite3 --version

# Extract jar file
echo "Extracting jar file ..."
EXTRACT_DIR=./tmp
java -jar ./pap23z-z20.jar -d $EXTRACT_DIR
# Initialize database
echo "Initializing database ..."
sqlite3 $EXTRACT_DIR/main/resources/database/appDB.db < $EXTRACT_DIR/main/resources/database/sql/create_table.sql
# Repack jar file
echo "Repacking jar file ..."
jar cfm ./pap23z-z20.jar $EXTRACT_DIR/META-INF/MANIFEST.MF -C $EXTRACT_DIR .

# Move jar file to installation directory
cp ./pap23z-z20.jar $INSTALL_DIR
echo "Creating application installation directory ..."

echo "[Desktop Entry]
Name = Library Management
Exec = java -jar $INSTALL_DIR/pap23z-z20.jar
Type = Application
Categories = Utility;" > /usr/share/applications/pap23z-z20.desktop

# Provide executable permissions
chmod +x $INSTALL_DIR/pap23z-z20.jar
echo "Installation complete."
