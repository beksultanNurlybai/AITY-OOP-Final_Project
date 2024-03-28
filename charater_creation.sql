PGDMP      7                |            character_creation    16.2    16.2 -    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16400    character_creation    DATABASE     �   CREATE DATABASE character_creation WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1252';
 "   DROP DATABASE character_creation;
                postgres    false            �            1259    16402    ability    TABLE     �   CREATE TABLE public.ability (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(200)
);
    DROP TABLE public.ability;
       public         heap    postgres    false            �            1259    16401    ability_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ability_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.ability_id_seq;
       public          postgres    false    216            �           0    0    ability_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.ability_id_seq OWNED BY public.ability.id;
          public          postgres    false    215            �            1259    16440 	   character    TABLE     �  CREATE TABLE public."character" (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    history character varying(200),
    power double precision NOT NULL,
    health double precision NOT NULL,
    armor double precision NOT NULL,
    dps double precision NOT NULL,
    class_id integer NOT NULL,
    ability_id integer NOT NULL,
    weapon_id integer NOT NULL,
    clothes_id integer NOT NULL
);
    DROP TABLE public."character";
       public         heap    postgres    false            �            1259    16439    character_id_seq    SEQUENCE     �   CREATE SEQUENCE public.character_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.character_id_seq;
       public          postgres    false    224            �           0    0    character_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.character_id_seq OWNED BY public."character".id;
          public          postgres    false    223            �            1259    16409    class_    TABLE     �   CREATE TABLE public.class_ (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(200),
    health double precision NOT NULL
);
    DROP TABLE public.class_;
       public         heap    postgres    false            �            1259    16408    class__id_seq    SEQUENCE     �   CREATE SEQUENCE public.class__id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.class__id_seq;
       public          postgres    false    218            �           0    0    class__id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.class__id_seq OWNED BY public.class_.id;
          public          postgres    false    217            �            1259    16428    clothes    TABLE     �   CREATE TABLE public.clothes (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(200),
    armor double precision NOT NULL,
    class_id integer NOT NULL
);
    DROP TABLE public.clothes;
       public         heap    postgres    false            �            1259    16427    clothes_id_seq    SEQUENCE     �   CREATE SEQUENCE public.clothes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.clothes_id_seq;
       public          postgres    false    222            �           0    0    clothes_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.clothes_id_seq OWNED BY public.clothes.id;
          public          postgres    false    221            �            1259    16416    weapon    TABLE     �   CREATE TABLE public.weapon (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    description character varying(200),
    dps double precision NOT NULL,
    class_id integer NOT NULL
);
    DROP TABLE public.weapon;
       public         heap    postgres    false            �            1259    16415    weapon_id_seq    SEQUENCE     �   CREATE SEQUENCE public.weapon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.weapon_id_seq;
       public          postgres    false    220            �           0    0    weapon_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.weapon_id_seq OWNED BY public.weapon.id;
          public          postgres    false    219            .           2604    16405 
   ability id    DEFAULT     h   ALTER TABLE ONLY public.ability ALTER COLUMN id SET DEFAULT nextval('public.ability_id_seq'::regclass);
 9   ALTER TABLE public.ability ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            2           2604    16443    character id    DEFAULT     n   ALTER TABLE ONLY public."character" ALTER COLUMN id SET DEFAULT nextval('public.character_id_seq'::regclass);
 =   ALTER TABLE public."character" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            /           2604    16412 	   class_ id    DEFAULT     f   ALTER TABLE ONLY public.class_ ALTER COLUMN id SET DEFAULT nextval('public.class__id_seq'::regclass);
 8   ALTER TABLE public.class_ ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            1           2604    16431 
   clothes id    DEFAULT     h   ALTER TABLE ONLY public.clothes ALTER COLUMN id SET DEFAULT nextval('public.clothes_id_seq'::regclass);
 9   ALTER TABLE public.clothes ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            0           2604    16419 	   weapon id    DEFAULT     f   ALTER TABLE ONLY public.weapon ALTER COLUMN id SET DEFAULT nextval('public.weapon_id_seq'::regclass);
 8   ALTER TABLE public.weapon ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �          0    16402    ability 
   TABLE DATA           8   COPY public.ability (id, name, description) FROM stdin;
    public          postgres    false    216   �2       �          0    16440 	   character 
   TABLE DATA           �   COPY public."character" (id, name, history, power, health, armor, dps, class_id, ability_id, weapon_id, clothes_id) FROM stdin;
    public          postgres    false    224   ]3       �          0    16409    class_ 
   TABLE DATA           ?   COPY public.class_ (id, name, description, health) FROM stdin;
    public          postgres    false    218   4       �          0    16428    clothes 
   TABLE DATA           I   COPY public.clothes (id, name, description, armor, class_id) FROM stdin;
    public          postgres    false    222   a4       �          0    16416    weapon 
   TABLE DATA           F   COPY public.weapon (id, name, description, dps, class_id) FROM stdin;
    public          postgres    false    220   5       �           0    0    ability_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.ability_id_seq', 2, true);
          public          postgres    false    215            �           0    0    character_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.character_id_seq', 2, true);
          public          postgres    false    223            �           0    0    class__id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.class__id_seq', 3, true);
          public          postgres    false    217            �           0    0    clothes_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.clothes_id_seq', 2, true);
          public          postgres    false    221            �           0    0    weapon_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.weapon_id_seq', 4, true);
          public          postgres    false    219            4           2606    16407    ability ability_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.ability
    ADD CONSTRAINT ability_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.ability DROP CONSTRAINT ability_pkey;
       public            postgres    false    216            <           2606    16445    character character_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."character"
    ADD CONSTRAINT character_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."character" DROP CONSTRAINT character_pkey;
       public            postgres    false    224            6           2606    16414    class_ class__pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.class_
    ADD CONSTRAINT class__pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.class_ DROP CONSTRAINT class__pkey;
       public            postgres    false    218            :           2606    16433    clothes clothes_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.clothes
    ADD CONSTRAINT clothes_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.clothes DROP CONSTRAINT clothes_pkey;
       public            postgres    false    222            8           2606    16421    weapon weapon_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.weapon
    ADD CONSTRAINT weapon_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.weapon DROP CONSTRAINT weapon_pkey;
       public            postgres    false    220            ?           2606    16451 #   character character_ability_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."character"
    ADD CONSTRAINT character_ability_id_fkey FOREIGN KEY (ability_id) REFERENCES public.ability(id);
 O   ALTER TABLE ONLY public."character" DROP CONSTRAINT character_ability_id_fkey;
       public          postgres    false    224    216    4660            @           2606    16446 !   character character_class_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."character"
    ADD CONSTRAINT character_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.class_(id);
 M   ALTER TABLE ONLY public."character" DROP CONSTRAINT character_class_id_fkey;
       public          postgres    false    218    224    4662            A           2606    16461 #   character character_clothes_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."character"
    ADD CONSTRAINT character_clothes_id_fkey FOREIGN KEY (clothes_id) REFERENCES public.clothes(id);
 O   ALTER TABLE ONLY public."character" DROP CONSTRAINT character_clothes_id_fkey;
       public          postgres    false    224    4666    222            B           2606    16456 "   character character_weapon_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public."character"
    ADD CONSTRAINT character_weapon_id_fkey FOREIGN KEY (weapon_id) REFERENCES public.weapon(id);
 N   ALTER TABLE ONLY public."character" DROP CONSTRAINT character_weapon_id_fkey;
       public          postgres    false    224    220    4664            >           2606    16434    clothes clothes_class_id_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.clothes
    ADD CONSTRAINT clothes_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.class_(id);
 G   ALTER TABLE ONLY public.clothes DROP CONSTRAINT clothes_class_id_fkey;
       public          postgres    false    4662    222    218            =           2606    16422    weapon weapon_class_id_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.weapon
    ADD CONSTRAINT weapon_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.class_(id);
 E   ALTER TABLE ONLY public.weapon DROP CONSTRAINT weapon_class_id_fkey;
       public          postgres    false    218    4662    220            �   `   x�3���KUp�/Rp����/UHN�S��K.JM,NU��/-R(.)J�K/���2���MU.�/��-rJ2RJ@Ri@�L�S���R���b���� � ~      �   �   x�Eͽ�0�����bIgOks�	�	�$���Fgg�m�5�-n�o�3�}�vO�^�u�#�q�ɏH3'A8�w�>�Q���PUtj��T�2��.�C�E���W��n���8f�`\�"�me����K��̟-럚�.Z)�p3�      �   M   x�3�NLJ-���,VH�I,.V 2���Rr�JR��8���9��K�(�+�Y��z��\1z\\\ ^�L      �   �   x�-��
�0Dϛ����A��Ƀ����m�MB��$��oR{YXf����(3ޢ�ocj��`��nE�~�d�s�le	q��7�(cqFYR��;G��hUOҶǻ���+r�E���V�.��w����]�.Z�ޭd��Hɔu�h�pR�F)��#I�      �   V  x�eQ�N�0=�_�#HӤ��83�i��4$.\��mC��r����8-7.Ql?�g?����Bk���{k���G�8�E�:U�x����x��8x��C��b�G:M,5�4
n��5��k��c�x�V�Z+�K��֢&�ߖi��ed��%����y���3M���ޙ����>g_͘h/�G4.g.��6���i��l(׷��QF���S�%b]�ߍ0�d-*�(& :-�X�����˶!������p��~�-DH�z�6,UY���L6�ćO���tSW�,Y���9#�E6�;�)��s��*���A�'�4�v�#Z+w�K>���(�_ϲ��     