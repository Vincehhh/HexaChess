# HexaChess

![Stars](https://img.shields.io/github/stars/Inc44/HexaChess?style=social)
![Forks](https://img.shields.io/github/forks/Inc44/HexaChess?style=social)
![Watchers](https://img.shields.io/github/watchers/Inc44/HexaChess?style=social)
![Repo Size](https://img.shields.io/github/repo-size/Inc44/HexaChess)
![Language Count](https://img.shields.io/github/languages/count/Inc44/HexaChess)
![Top Language](https://img.shields.io/github/languages/top/Inc44/HexaChess)
[![Issues](https://img.shields.io/github/issues/Inc44/HexaChess)](https://github.com/Inc44/HexaChess/issues?q=is%3Aopen+is%3Aissue)
![Last Commit](https://img.shields.io/github/last-commit/Inc44/HexaChess?color=red)
[![Release](https://img.shields.io/github/release/Inc44/HexaChess.svg)](https://github.com/Inc44/HexaChess/releases)
[![Sponsor](https://img.shields.io/static/v1?label=Sponsor&message=%E2%9D%A4&logo=GitHub&color=%23fe8e86)](https://github.com/sponsors/Inc44)

Gliński's hexagonal chess game implementation in Java.

![HexaChess](HexaChess.png)

## 🚀 Installation

### From Source

```bash
git clone https://github.com/Inc44/HexaChess.git
```

#### Eclipse

```
Open Projects from File System...
Import source: `path/to/HexaChess`
Finish
```

```
Build Path > Configure Build Path... > Libraries > Modulepath
Add Library... > User Library > Next > User Libraries... > New...
User library name: `JavaFX21`
Add External JARs...
path/to/javafx-sdk-21/lib/*.jar
Apply and Close
Finish
Apply and Close
```

```
Run Configurations > Java Application > Main > Arguments
VM arguments: `--module-path "path/to/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.graphics`
```

#### Terminal

Ensure these binaries are in your system's PATH:

- Apache Maven - Version 3.9.11 tested
- GraalVM CE Gluon - Version 23-dev+25.1 tested
- JDK 21 - Version 21.0.9 tested (for Linux)
- Visual Studio Community 2022 - Version 17.14.22 tested (for Windows) (optional, to build)

On Arch Linux:

```bash
sudo pacman -S maven jdk21-openjdk
sudo archlinux-java set java-21-openjdk
export GRAALVM_HOME="path/to/graalvm-java23-gluon"
```

```bash
mvn -v
java --version
$GRAALVM_HOME/bin/native-image --version
```

On Windows:

- Download binary archive and extract from [Apache Maven](https://maven.apache.org/download.cgi)
- Download binary archive and extract from [GraalVM CE Gluon](https://github.com/gluonhq/graal/releases)
- Download and install from [Visual Studio Community 2022](https://visualstudio.microsoft.com/vs/community) (optional, to build)
- Download and install from [WiX Toolset](https://github.com/wixtoolset/wix3/releases) (optional, to produce .msi installer)

##### Visual Studio Installer

Language packs

- English

Individual components

- C++/CLI support for v143 build tools (Latest)
- MSVC v143 - VS 2022 C++ x64/x86 build tools (Latest)
- Windows Universal CRT SDK
- Windows 11 SDK (10.0.26100.7175)

```cmd
setx /M PATH "%PATH%;path\to\apache-maven\bin"
setx /M GRAALVM_HOME "path\to\graalvm-java23-gluon"
```

```cmd
mvn -v
"%GRAALVM_HOME%"\bin\native-image --version
```

```bash
cd HexaChess
mvn clean gluonfx:run
```

## 🛠️ Build from Source

### Linux

```bash
mvn clean gluonfx:build
mvn gluonfx:package
```

### Windows

Open `x64 Native Tools Command Prompt for VS 2022` instead of regular `cmd`

```cmd
mvn clean gluonfx:build
mvn gluonfx:package & :: (optional, to produce .msi installer)
```

### Android

```bash
mvn clean gluonfx:build -Pandroid
mvn gluonfx:package -Pandroid
```

## 🐛 Bugs

- The game continues despite checkmate, stalemate, or threefold repetition.

## ⛔ Known Limitations

- The existing version is written with Java Swing, which may not provide the best user/dev experience compared to JavaFX.
- The existing version may be using material not covered during lessons (e.g., enum, AWT, Swing, URI, ImageIO, Thread, etc.).

## 🚧 TODO

- [ ] **Different Screen Sizes**: Adapt UI for various screen dimensions.
- [ ] **Enemy Movements**: Highlight enemy possible moves (in red).
- [ ] **Game Settings**: Implement configurable game options (e.g., white/black, aim assist).
- [ ] **Difficulty/Compute Time**: Adjust AI difficulty based on compute time (via max depth).
- [ ] **Aim Assist**: Highlight better moves with darker color shades.
- [ ] **Improve Performance**: Use multithreading/parallel execution.
- [ ] **LLM Chess Player**: Add a large language model-based chess player for fun :) (and hallucinations (domain expansion, illegal moves, self-capture, etc.)).

## 🙏 Thanks

Creators of:

- [Apache Maven](https://maven.apache.org)
- [Gluon Documentation](https://docs.gluonhq.com)
- [Gluon Samples](https://github.com/gluonhq/gluon-samples)
- [GluonFX plugin for Maven](https://github.com/gluonhq/gluonfx-maven-plugin)
- [GraalVM CE Gluon](https://github.com/gluonhq/graal)
- [JavaFX](https://openjfx.io)

## 🤝 Contribution

Contributions, suggestions, and new ideas are heartily welcomed. If you're considering significant modifications, please initiate an issue for discussion before submitting a pull request.

## 📜 License

[![MIT](https://img.shields.io/badge/License-MIT-lightgrey.svg)](https://opensource.org/licenses/MIT)

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 💖 Support

[![BuyMeACoffee](https://img.shields.io/badge/Buy%20Me%20a%20Coffee-ffdd00?style=for-the-badge&logo=buy-me-a-coffee&logoColor=black)](https://buymeacoffee.com/xamituchido)
[![Ko-Fi](https://img.shields.io/badge/Ko--fi-F16061?style=for-the-badge&logo=ko-fi&logoColor=white)](https://ko-fi.com/inc44)
[![Patreon](https://img.shields.io/badge/Patreon-F96854?style=for-the-badge&logo=patreon&logoColor=white)](https://www.patreon.com/Inc44)