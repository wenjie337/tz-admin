@set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
@echo ѡ�񹤳̽�Ҫ���еĻ�����������ź󰴻س���������
@echo.
@echo #######################################################################
@echo ##                       1. ��������                                 ##
@echo ##          	         2. ���Ի���                                 ##
@echo ##                       3. Ԥ��������                               ##
@echo ##                       4. ��������                                 ##
@echo #######################################################################
@echo.

@set env=""

:input
@set /p input=��������ţ�
@if %input%==1 (
	@set env=d
) else if %input%==2 (
	@set env=t
) else if %input%==3 (
	@set env=pre
) else if %input%==4 (
	@set env=p
) else (
	@echo �Ƿ������룡�����ԣ�
	@echo.
	goto input
)

@echo.
@echo ���ڴ�������Եȡ���
@echo.
@set GRADLE_OPTS=-Xmx512m -XX:MaxPermSize=128m
@call gradle  -x test --stacktrace --refresh-dependencies -Penv=%env% clean build 

@echo.
@if %ERRORLEVEL%==0 (
	@echo ���̹�����ϣ�
) else (
	@echo ���̹���ʧ�ܣ�
)
@echo.
@echo ���̴����ϣ����ڵ�ǰĿ¼�²���war����
@echo.
@echo ��������˳����� & pause>nul