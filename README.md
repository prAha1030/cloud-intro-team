# Cloud Architecture 활용한 팀원 소개

***

## 목차

1. [🌘 프로젝트에 들어가기에 앞서](#-Before)
2. [🌕 프로젝트를 하며](#-During)
3. [🌒 프로젝트를 마치며](#-After)

<br>

***

## 🌘 Before

- [AWS Budget 설정](#AWS-Budget-설정)
- [프로젝트 구성](#프로젝트-구성)
- [Cloud Architecture 설계](#Cloud-Architecture-설계)

---

### AWS Budget 설정

예산 폭탄 방지를 위한 예산 설정

<img width="640" height="420" alt="Image" src="https://github.com/user-attachments/assets/75805545-55f2-4547-a808-c16b9e8c53c4" />

<br><br>

### 프로젝트 구성

- **팀원 저장**
  - 팀원 이름, 나이, mbti의 정보가 팀원 저장
- **팀원 조회**
  - 저장된 팀원들을 조회
- **팀원 프로필 사진 저장**
  - 팀원의 프로필 사진을 업로드하여 저장
- **팀원 프로필 사진 조회**
  - 저장된 팀원의 프로필 사진을 조회하여 다운로드 가능
- **상태 모니터링**
  - Actuator 라이브러리를 이용한 헬스체크

<br>

### Cloud Architecture 설계

- EC2 : 서버 관리
- RDS : DB 관리
- S3 : 파일/이미지 관리

<br><br>

[🗺️ 목차로 돌아가기](#목차)

---

## 🌕 During

1. [VPC의 public subnet에 연결한 EC2 생성](#EC2의-퍼블릭-IP)
2. [Parameter Store 설정](#Actuator-info)
3. [VPC의 public subnet에 연결한 RDS 생성](#RDS-보안그룹-인바운드-규칙)
4. [모든 퍼블릭 액세스 차단한 S3 버킷 생성](#S3-버킷-생성)
5. [Docker & CI/CD 파이프라인으로 자동 배포 구축](#Docker--CICD-파이프라인-구축)
6. [보안 도메인 연결](#ALB--ASG--HTTPS)

---

### EC2의 퍼블릭 IP

퍼블릭 서브넷에 거주하던 EC2의 퍼블릭 IP

<img width="137" height="56" alt="Image" src="https://github.com/user-attachments/assets/aa087af2-0f8f-4b40-8188-61371a174489" />

<br><br>

### Actuator info

Parameter Store를 통해 team-name 값 설정

**But** 기존 EC2의 퍼블릭 서브넷에서 private subnet으로 이사하여 현재는 접속불가 URL

<img width="700" height="420" alt="Image" src="https://github.com/user-attachments/assets/41313a27-27e3-456b-82ff-c623825a4164" />

private subnet으로 이사하여 https가 적용된 도메인으로 변경

[HTTPS 적용된 도메인 URL 확인하러 가기](#HTTPS가-적용된-도메인-URL)

<br><br>

### RDS 보안그룹 인바운드 규칙

EC2의 보안그룹 ID만 등록하여 EC2를 통한 접근 설계

<img width="800" height="160" alt="Image" src="https://github.com/user-attachments/assets/43555776-96c2-41e8-92a5-2e01920e0703" />

<br><br>

### S3 버킷 생성

S3 버킷을 통한 프로필 사진 업로드 및 다운로드 기능 구현

* 이미지 다운로드 시 Presigned URL을 통해 다운 가능 (유효기간 : 7일)

<br><br>

### Docker & CI/CD 파이프라인 구축

#### GitHub Actions Success

GitHub Actions(CI/CD)를 이용한 자동 배포 시스템

<img width="1400" height="460" alt="Image" src="https://github.com/user-attachments/assets/a13caab2-866d-47ce-b073-078e86e2f5bc" />

#### EC2 실행 중인 컨테이너 목록

배포를 통해 생성되고 실행 중인 Docker container

<img width="767" height="71" alt="Image" src="https://github.com/user-attachments/assets/1a73f838-7181-4ad6-8d8b-61613ab75de8" />

<br><br>

### ALB + ASG + HTTPS

- 보안을 강화하기 위해 Nat Gateway를 생성하여 EC2와 RDS를 private subnet으로 이사

- HTTPS가 적용된 도메인을 연결하기 위해 ALB & ASG 설정

#### Target Group

ALB가 연결할 EC2(Target)들의 그룹

<img width="1203" height="187" alt="Image" src="https://github.com/user-attachments/assets/2533156d-cd32-417c-ab8b-bc5373a669f2" />

#### Target

ALB가 연결한 EC2(Target)들의 상태 확인 결과 : Healthy(정상)

<img width="593" height="249" alt="Image" src="https://github.com/user-attachments/assets/9df54507-a53f-42a1-9aa4-9685a30b55e7" />

<br><br>

[🗺️ 목차로 돌아가기](#목차)

---

## 🌒 After

- [프로젝트를 하며 마주친 문제들](#마주친-문제들)
- [프로젝트 배포](#HTTPS가-적용된-도메인-URL)

---

### 마주친 문제들

1. [Parameter Store 경로 문제](https://velog.io/@zkolonm28/AWS-Parameter-Store)
2. [GitHub workflow deploy의 new db 문제](https://velog.io/@zkolonm28/GitHub-Actions)
3. [Target Group의 상태검사 문제](https://velog.io/@zkolonm28/ALB%EC%9D%98-Target-Group-%EA%B2%89%ED%95%A5%EA%B8%B0)

<br>

### HTTPS가 적용된 도메인 URL

#### ⬇️⬇️⬇️ Actuator info 확인하러 가기 ⬇️⬇️⬇️

https://introgatteam.click/actuator/info

<br><br>

[🗺️ 목차로 돌아가기](#목차)