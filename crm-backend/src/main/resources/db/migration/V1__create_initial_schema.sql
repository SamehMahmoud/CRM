CREATE TABLE APP_USER(
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  username varchar(50) UNIQUE NOT NULL,
  phone varchar(50),
  email varchar(50) UNIQUE NOT NULL,
  first_name varchar(50) NULL,
  last_name varchar(50) null,
  status varchar(20) NOT NULL
);


CREATE TABLE TENANT(
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name varchar(50) NOT NULL,
  phone varchar(50),
  email varchar(50) UNIQUE NOT NULL ,
  status varchar(20) NOT NULL,
  created_at timestamptz NOT NULL,
  created_by uuid NOT NULL
);
CREATE INDEX tenant_created_by_idx ON TENANT(created_by);
CREATE INDEX tenant_name_idx ON TENANT(name);



CREATE TABLE USER_TENANT(
    user_id uuid NOT NULL references APP_USER(id),
    tenant_id uuid NOT NULL references TENANT(id),
    role varchar(50) NOT NULL,
    primary key(user_id, tenant_id)
);
CREATE INDEX user_tenant_idx ON USER_TENANT(tenant_id);

CREATE TABLE CUSTOMER(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(50) NOT NULL,
    tenant_id uuid NOT NULL references TENANT(id),
    type varchar(20) NOT NULL,
    created_at timestamptz NOT NULL,
    created_by uuid NOT NULL references APP_USER(id)
);
CREATE INDEX customer_tenant_idx on CUSTOMER(tenant_id);
CREATE INDEX customer_user_idx on CUSTOMER(created_by);

CREATE TABLE CONTACT_POINT(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id uuid NOT NULL references CUSTOMER(id),
    type varchar NOT NULL,
    contact_value varchar(50) NOT NULL,
    normalized_value varchar(50) NOT NULL,
    tenant_id uuid NOT NULL references TENANT(id),
    created_at timestamptz NOT NULL,
    created_by uuid NOT NULL references APP_USER(id)
);
CREATE UNIQUE INDEX contact_point_email_unique_idx ON CONTACT_POINT(tenant_id, normalized_value) WHERE type = 'EMAIL';
CREATE INDEX contact_point_customer_idx on CONTACT_POINT(customer_id);
CREATE INDEX contact_point_nval_idx ON CONTACT_POINT(normalized_value);