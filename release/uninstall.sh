#!/bin/bash
HOME_DIR=$(eval echo ~"${SUDO_USER}")
INSTALL_DIR=$HOME_DIR/.LibMan
DESKTOP_FILE=/usr/share/applications/LibMan.desktop

echo "Uninstalling OpenJDK 17 ..."
yes | apt remove openjdk-17-jdk openjdk-17-jre > /dev/null 2>&1

echo "Uninstalling sqlite3 ..."
apt-get remove -y sqlite3 > /dev/null 2>&1
echo "Uninstalling application ..."
rm -rf "$INSTALL_DIR"
rm -rf $DESKTOP_FILE
echo "Uninstallation complete."
