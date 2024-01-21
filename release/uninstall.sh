#!/bin/bash
INSTALL_DIR=/opt/LibMan
DESKTOP_FILE=/usr/share/applications/LibMan.desktop

echo "Uninstalling application ..."
rm -rf $INSTALL_DIR
rm -rf $DESKTOP_FILE
echo "Uninstallation complete."
