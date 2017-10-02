FROM postgres:9.6-alpine

COPY pg_hba.conf /usr/share/postgresql/9.6/
COPY postgresql.conf /usr/share/postgresql/9.6/
ADD docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

ENV POSTGRES_USER exampleuser
ENV POSTGRES_PASSWORD changeme
ENV POSTGRES_DB exampledb