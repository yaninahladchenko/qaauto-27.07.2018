*Setup on OS X:*
1. Install Homebrew package manager if not installed (https://brew.sh/)
2. Install JDK 10 via Homebrew
`brew cask install java`
3. Verify that JDK 10 was installed
`java -version`
4. Install Maven via Homebrew
`brew install maven`
5. Verify that Maven was installed
`mvn -version`
6.  Make sure you have Firefox version 61 installed
7. Download 'Community' version of Intellij IDEA: https://www.jetbrains.com/idea/download/#section=windows
8. Install IntelliJ IDEA for your operating system. For macOS:
Double-click the ideaIC.dmg or ideaIU.dmg file you have downloaded to mount the macOS disk image.
Copy IntelliJ IDEA to the Applications folder.
9. Download latest version of geckodriver ( geckodriver-v0.21.0-macos.tar.gz):
https://github.com/mozilla/geckodriver/releases
10. Unzip geckodriver.tar.gz
11. Copy geckodriver executable into any folder that is in your PATH env variable value.
Example: mv geckodriver /usr/local/bin
