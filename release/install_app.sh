#!/bin/bash
JAR_FILE=library-management.jar
DB_FILE=main/resources/database/appDB.db
SQL_SCRIPT=main/resources/database/sql/create_table.sql
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
mkdir -d $EXTRACT_DIR
jar xvf $EXTRACT_DIR/$JAR_FILE
# Initialize database
echo "Initializing database ..."
# Modify write permissions
chmod +w $EXTRACT_DIR/$DB_FILE
sqlite3 $EXTRACT_DIR/$DB_FILE < $EXTRACT_DIR/$SQL_SCRIPT
# Repack jar file
echo "Repacking jar file ..."
jar cfm ./$JAR_FILE $EXTRACT_DIR/META-INF/MANIFEST.MF -C $EXTRACT_DIR .

# Move jar file to installation directory
cp ./$JAR_FILE $INSTALL_DIR
echo "Creating application installation directory ..."

echo "[Desktop Entry]
Name = Library Management
Exec = java -jar $INSTALL_DIR/library-management.jar
Type = Application
Categories = Utility;" > /usr/share/applications/LibMan.desktop

# Provide executable permissions
chmod +x $INSTALL_DIR/library-management.jar
echo "Installation complete."
