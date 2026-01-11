# Compose Nav3 Showcase

Navigation 3 기반 AOSP 스타일 설정 앱 예제 프로젝트입니다.

## Tech Stack

- **Kotlin** + **Jetpack Compose**
- **Navigation 3** - 최신 Navigation 라이브러리
- **Hilt** - 의존성 주입
- **Room** - 로컬 데이터베이스
- **Material 3** - AOSP 스타일 UI

## Architecture

멀티 모듈 클린 아키텍처를 적용했습니다.

```
app/
├── core/
│   ├── data/          # Room DB, Repository, DI Module
│   ├── ui/            # 공통 UI 컴포넌트, Theme
│   └── navigation/    # Navigation Keys
├── feature/
│   ├── main/          # 메인 설정 화면
│   ├── display/       # 디스플레이/테마 설정
│   ├── storage/       # 스토리지 관리
│   ├── about/         # 앱 정보 및 라이선스
│   └── developer/     # 개발자 옵션
```

## Features

- 테마 설정 (Light/Dark/System)
- Dynamic Colors 지원
- 스토리지 관리
- 개발자 옵션
- 라이선스 정보

## Requirements

- Android Studio Ladybug 이상
- minSdk 30
- compileSdk 36

## License

MIT License