FROM  mysql:5.7

LABEL  authtor="wangjiayu"

COPY mysqld.cnf /etc/mysql/mysql.conf.d/

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone
