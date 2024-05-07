# Projakt

## Temat

Program zarządzania biblioteką

## Wymagania

- Program napisany w Java 17.
- Połączenie z bazą danych SQLite3
- Aplikacja napisana z użyciem biblioteki Swing


## Członkowie

- Jakub Wróblewski
- Wojciech Sarwiński
- Binh Vuong Le Duc
- Michał Sadlej

## Instalacja

### Clone repository from gitlab
```bash
git clone https://gitlab-stud.elka.pw.edu.pl/wsarwins/pap2023z-z20.git
```
### Redirection to release directory which contains installation and .jar files
```bash
cd pap2023z-z20/release
```

### Run the installation script with root privileges
```bash
sudo ./install.sh
```
The script will be installed in the home directory of the current user, inside folder .LibMan.

The script will also create a desktop shortcut for the application.

## Odinstalowanie

### Redirection to uninstallation script
```bash
cd pap2023z-z20/release
```
### Run the uninstallation script with root privileges
```bash
sudo ./uninstall.sh
```

## Testowanie
Program was tested with JUnit 5.7.2, test files are located in 
```
pap2023z-z20/src/test/main/java/org/papz20/
```
