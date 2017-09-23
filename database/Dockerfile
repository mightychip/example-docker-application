FROM postgres:9.6-alpine

RUN mkdir -p /images/

#For now, we'll just assume basic config works... this thing will probably not let us connect from a non-local address
#or something with my luck...
COPY pg_hba.conf /usr/share/postgresql/9.6/
COPY postgresql.conf /usr/share/postgresql/9.6/
ADD docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

ENV POSTGRES_USER exampleuser
ENV POSTGRES_PASSWORD changeme
ENV POSTGRES_DB exampledb