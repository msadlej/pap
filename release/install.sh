#!/bin/bash

INSTALL_DIR=/opt/app
mkdir -p $INSTALL_DIR

cp ./pap23z-z20.jar $INSTALL_DIR
echo "Copy done"

echo "[Desktop Entry]
Name = Library Management
Exec = java -jar $INSTALL_DIR/pap23z-z20.jar
Type = Application
Categories = Utility;" > /usr/share/applications/pap23z-z20.desktop

chmod +x $INSTALL_DIR/pap23z-z20.jar

echo "Installation complete."
