PGDMP     
                    {         	   Fundacion    15.3    15.3 ,    @           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            A           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            B           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            C           1262    16398 	   Fundacion    DATABASE     �   CREATE DATABASE "Fundacion" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE "Fundacion";
                postgres    false            �            1259    16399    administrativo    TABLE     j   CREATE TABLE public.administrativo (
    id integer NOT NULL,
    cargo character varying(70) NOT NULL
);
 "   DROP TABLE public.administrativo;
       public         heap    postgres    false            �            1259    16402    beneficiados    TABLE     ~   CREATE TABLE public.beneficiados (
    id_beneficiados integer NOT NULL,
    id_proyecto integer,
    id_comunidad integer
);
     DROP TABLE public.beneficiados;
       public         heap    postgres    false            �            1259    16405 	   comunidad    TABLE     �   CREATE TABLE public.comunidad (
    cod_comunidad integer NOT NULL,
    nombre character varying(60) NOT NULL,
    etnia character varying(60),
    "poblacion_niños" integer NOT NULL,
    idrepresentante integer
);
    DROP TABLE public.comunidad;
       public         heap    postgres    false            �            1259    16408    empleado    TABLE     �   CREATE TABLE public.empleado (
    id integer NOT NULL,
    nombre character varying(60) NOT NULL,
    salario integer NOT NULL,
    telefono character varying(20) NOT NULL
);
    DROP TABLE public.empleado;
       public         heap    postgres    false            �            1259    16411    niños    TABLE     �   CREATE TABLE public."niños" (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL,
    apellido character varying(20) NOT NULL,
    fecha_nacimiento date NOT NULL,
    comunidad integer NOT NULL,
    sexo character varying(20)
);
    DROP TABLE public."niños";
       public         heap    postgres    false            �            1259    16414 	   objetivos    TABLE     �   CREATE TABLE public.objetivos (
    cod_objetivos integer NOT NULL,
    descripcion character varying(300),
    nota integer,
    idproyecto integer
);
    DROP TABLE public.objetivos;
       public         heap    postgres    false            �            1259    16417 
   participan    TABLE     }   CREATE TABLE public.participan (
    cod_participan integer NOT NULL,
    id_proyecto integer,
    id_profesional integer
);
    DROP TABLE public.participan;
       public         heap    postgres    false            �            1259    16420    profesional    TABLE     q   CREATE TABLE public.profesional (
    id integer NOT NULL,
    especializacion character varying(70) NOT NULL
);
    DROP TABLE public.profesional;
       public         heap    postgres    false            �            1259    16423    proyecto    TABLE     c  CREATE TABLE public.proyecto (
    codproyecto integer NOT NULL,
    titulo character varying(50) NOT NULL,
    descripcion character varying(100) NOT NULL,
    tema character varying(90),
    presupuesto integer NOT NULL,
    alcance character varying(100),
    fechainicio date,
    fechafinal date,
    idresponsable integer,
    evaluacion integer
);
    DROP TABLE public.proyecto;
       public         heap    postgres    false            �            1259    16426    representante    TABLE     �   CREATE TABLE public.representante (
    id integer NOT NULL,
    nombre character varying(70) NOT NULL,
    apellido character varying(20),
    telefono character varying(20)
);
 !   DROP TABLE public.representante;
       public         heap    postgres    false            4          0    16399    administrativo 
   TABLE DATA           3   COPY public.administrativo (id, cargo) FROM stdin;
    public          postgres    false    214   �4       5          0    16402    beneficiados 
   TABLE DATA           R   COPY public.beneficiados (id_beneficiados, id_proyecto, id_comunidad) FROM stdin;
    public          postgres    false    215   55       6          0    16405 	   comunidad 
   TABLE DATA           f   COPY public.comunidad (cod_comunidad, nombre, etnia, "poblacion_niños", idrepresentante) FROM stdin;
    public          postgres    false    216   �5       7          0    16408    empleado 
   TABLE DATA           A   COPY public.empleado (id, nombre, salario, telefono) FROM stdin;
    public          postgres    false    217   C6       8          0    16411    niños 
   TABLE DATA           [   COPY public."niños" (id, nombre, apellido, fecha_nacimiento, comunidad, sexo) FROM stdin;
    public          postgres    false    218   :8       9          0    16414 	   objetivos 
   TABLE DATA           Q   COPY public.objetivos (cod_objetivos, descripcion, nota, idproyecto) FROM stdin;
    public          postgres    false    219   m:       :          0    16417 
   participan 
   TABLE DATA           Q   COPY public.participan (cod_participan, id_proyecto, id_profesional) FROM stdin;
    public          postgres    false    220   5<       ;          0    16420    profesional 
   TABLE DATA           :   COPY public.profesional (id, especializacion) FROM stdin;
    public          postgres    false    221   �<       <          0    16423    proyecto 
   TABLE DATA           �   COPY public.proyecto (codproyecto, titulo, descripcion, tema, presupuesto, alcance, fechainicio, fechafinal, idresponsable, evaluacion) FROM stdin;
    public          postgres    false    222   �<       =          0    16426    representante 
   TABLE DATA           G   COPY public.representante (id, nombre, apellido, telefono) FROM stdin;
    public          postgres    false    223   ,?       �           2606    16430 "   administrativo administrativo_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.administrativo
    ADD CONSTRAINT administrativo_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.administrativo DROP CONSTRAINT administrativo_pkey;
       public            postgres    false    214            �           2606    16432    beneficiados beneficiados_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.beneficiados
    ADD CONSTRAINT beneficiados_pkey PRIMARY KEY (id_beneficiados);
 H   ALTER TABLE ONLY public.beneficiados DROP CONSTRAINT beneficiados_pkey;
       public            postgres    false    215            �           2606    16434    comunidad comunidad_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.comunidad
    ADD CONSTRAINT comunidad_pkey PRIMARY KEY (cod_comunidad);
 B   ALTER TABLE ONLY public.comunidad DROP CONSTRAINT comunidad_pkey;
       public            postgres    false    216            �           2606    16436    empleado empleado_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.empleado DROP CONSTRAINT empleado_pkey;
       public            postgres    false    217            �           2606    16438    niños niños_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."niños"
    ADD CONSTRAINT "niños_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."niños" DROP CONSTRAINT "niños_pkey";
       public            postgres    false    218            �           2606    16440    objetivos objetivos_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.objetivos
    ADD CONSTRAINT objetivos_pkey PRIMARY KEY (cod_objetivos);
 B   ALTER TABLE ONLY public.objetivos DROP CONSTRAINT objetivos_pkey;
       public            postgres    false    219            �           2606    16442    participan participan_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.participan
    ADD CONSTRAINT participan_pkey PRIMARY KEY (cod_participan);
 D   ALTER TABLE ONLY public.participan DROP CONSTRAINT participan_pkey;
       public            postgres    false    220            �           2606    16444    profesional profesional_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.profesional
    ADD CONSTRAINT profesional_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.profesional DROP CONSTRAINT profesional_pkey;
       public            postgres    false    221            �           2606    16446    proyecto proyecto_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (codproyecto);
 @   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT proyecto_pkey;
       public            postgres    false    222            �           2606    16448     representante representante_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.representante
    ADD CONSTRAINT representante_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.representante DROP CONSTRAINT representante_pkey;
       public            postgres    false    223            �           2606    16449    niños fk_comunidad    FK CONSTRAINT     �   ALTER TABLE ONLY public."niños"
    ADD CONSTRAINT fk_comunidad FOREIGN KEY (comunidad) REFERENCES public.comunidad(cod_comunidad);
 ?   ALTER TABLE ONLY public."niños" DROP CONSTRAINT fk_comunidad;
       public          postgres    false    218    216    3213            �           2606    16454    beneficiados fk_comunidad    FK CONSTRAINT     �   ALTER TABLE ONLY public.beneficiados
    ADD CONSTRAINT fk_comunidad FOREIGN KEY (id_comunidad) REFERENCES public.comunidad(cod_comunidad);
 C   ALTER TABLE ONLY public.beneficiados DROP CONSTRAINT fk_comunidad;
       public          postgres    false    216    3213    215            �           2606    16459    administrativo fk_idadmin    FK CONSTRAINT     v   ALTER TABLE ONLY public.administrativo
    ADD CONSTRAINT fk_idadmin FOREIGN KEY (id) REFERENCES public.empleado(id);
 C   ALTER TABLE ONLY public.administrativo DROP CONSTRAINT fk_idadmin;
       public          postgres    false    214    3215    217            �           2606    16464    profesional fk_idpro    FK CONSTRAINT     q   ALTER TABLE ONLY public.profesional
    ADD CONSTRAINT fk_idpro FOREIGN KEY (id) REFERENCES public.empleado(id);
 >   ALTER TABLE ONLY public.profesional DROP CONSTRAINT fk_idpro;
       public          postgres    false    221    3215    217            �           2606    16469    participan fk_profesional    FK CONSTRAINT     �   ALTER TABLE ONLY public.participan
    ADD CONSTRAINT fk_profesional FOREIGN KEY (id_profesional) REFERENCES public.profesional(id);
 C   ALTER TABLE ONLY public.participan DROP CONSTRAINT fk_profesional;
       public          postgres    false    220    3223    221            �           2606    16474    objetivos fk_proyecto    FK CONSTRAINT     �   ALTER TABLE ONLY public.objetivos
    ADD CONSTRAINT fk_proyecto FOREIGN KEY (idproyecto) REFERENCES public.proyecto(codproyecto);
 ?   ALTER TABLE ONLY public.objetivos DROP CONSTRAINT fk_proyecto;
       public          postgres    false    219    3225    222            �           2606    16479    beneficiados fk_proyecto    FK CONSTRAINT     �   ALTER TABLE ONLY public.beneficiados
    ADD CONSTRAINT fk_proyecto FOREIGN KEY (id_proyecto) REFERENCES public.proyecto(codproyecto);
 B   ALTER TABLE ONLY public.beneficiados DROP CONSTRAINT fk_proyecto;
       public          postgres    false    222    3225    215            �           2606    16484    participan fk_proyecto    FK CONSTRAINT     �   ALTER TABLE ONLY public.participan
    ADD CONSTRAINT fk_proyecto FOREIGN KEY (id_proyecto) REFERENCES public.proyecto(codproyecto);
 @   ALTER TABLE ONLY public.participan DROP CONSTRAINT fk_proyecto;
       public          postgres    false    222    3225    220            �           2606    16489    comunidad fk_representante    FK CONSTRAINT     �   ALTER TABLE ONLY public.comunidad
    ADD CONSTRAINT fk_representante FOREIGN KEY (idrepresentante) REFERENCES public.representante(id);
 D   ALTER TABLE ONLY public.comunidad DROP CONSTRAINT fk_representante;
       public          postgres    false    3227    216    223            �           2606    16494    proyecto fk_responsable    FK CONSTRAINT     �   ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT fk_responsable FOREIGN KEY (idresponsable) REFERENCES public.administrativo(id);
 A   ALTER TABLE ONLY public.proyecto DROP CONSTRAINT fk_responsable;
       public          postgres    false    222    214    3209            4   V   x�34�,NM.J-I,���24A�"x�\�f(<s ��,393�X!=5/�(1'���Ђ�71/1=�����42������\c� N*5A      5   C   x���A�7;�Ĺs���0�B����I��H,��y��)Ov���I���ɑ/��+{������      6   �   x�-���@E�;cdx�%����Q
��l�"�	�[��;F���9s'�[TQ;;\e���8Ao���h�`bF�Q����NJ��|�G�ԥ��8C/����Jr���My9vJ
�Bo�w�Gp��73vĻ����Z]X�Wd��q�&���j���$�����u{�*�Hr�m��S�9�      7   �  x�E��n� ���S�T���ؤI�(�*�R��fwQ\;U���ޭ��ӧ�o~��b�2(�.PHA+���Xj���O�F@T����P(Y�n��>��K��N� :� �,S�#�&����y�&�6P;�$
t�)��%�4��[� yOP �0�x�5��t��u��
���4h-#��c��<���S>���J�УAH�����=M|���:j� )�QF'H��<�>����:�S���R:��{��7�B�ؐ���]���ڐNz�m �E��1�p�r�ιKS��5&��gDh����{L#�S�slA\^��Dۊ1�P�>W��9�]s2WH�PX!%�5x�sײ�Rv��JY�KN}�q�o���Z굚\8wuJ��Fm�X�.�Z\�I�4�y��o��.T������o�X�~�'\1�0����yh��W�d.Ah��s���1�c��bD�87����i�+���O���$��      8   #  x�]��r�0�חwQF��ߒ&m��Ȱ��
cF�R��"o�e��~�^���x��çsϹ"�ĝ+��U����Ar�1�3�Gx�j����Y,`�Z��0H�q���^�2�2�A
�W�2�V�F��������\{>6=<g\�R��~V�SZ#}���lzu�x�Dge��IC���r��8�b�!X�Y	�1l�7���t�B2�]{N`��	��YX�_��.y����£'n��E�3ꦊb�fO����4�G�O��wٺ��:����99���L8|/]X��m̟f�{�LD��j�����u�Ix��������e��Q2$�\"�r�:�`/b��I^׌�pK;���^��oؠ��0/�G*�������GwJ�`�R��3�c)� e��aD7u���2',�ط���F�nծ����w�nϣO��`6!Swh�-z�7t=0�7�G^8��Y�Y�j�=y���Vk���V���dtH#XP�4��
� }�!mNI�\6�6�2r�-�/&V��/Ͻ��6�>H��@���f�84:      9   �  x�m�Mn�0�לS���g�I����Ͳ�̺�Ȓ+Y�k��XIٞ� ]0���HQ,����1��((�Q��a��E�ᯄ����Tp�t�j���X��2,6;T�	���9�~�)B��-G��ѐ��i%t'�pDD�:<�9<k{�r���6^���vr�+��t4��7�p�zY�jb�����߀�&WϦn߸��Ъ1��\�Tpo�4���"��5|O7Ӭ��o�k�@�f�s�V������Ie��G�P��\U�{x6#�ԑ�ڊ�\;����4�,�E�
x��w"��ŗ֟V�%��9��k�����SDّ�v��k���oLs)�j:�D=/�D�Eo�����lg�z�<�*���bQL��Χe���&�ζ����ņ�����r����7zI�W�a����]qry�SB�[�-��6��?è�      :   A   x�̹�@���(�#�']/����2Bɰ���C�e��.m��:�=*ږ�k[��$��}�]      ;   [   x�3�)JLJ�JL�/R(�O�L��2��O��+���O��2Ƣ��3�83"o�����b�aQj�雚T�ecXbQeh�0����=... L-7�      <   +  x���K��@���)��q��cI��DAA�m6�]�5ݤ(+��b�n&$AF�Ʀ����,�)l�M /z؅��GG��ڊ�|o�Ot��xP6�Nd:e����dU0������[M�������R�˶(�h&���F�z�r�A��_�pE���'�B����/Us�6�������_{XG҉��z!
�:�b;���ώ�z��<��/��_ETEՌe�x���!��8���
�݀����S^um�?��^u	��:��z���e��ʼ��b˩��5[�z�Ū��<.���ո6Q�rZH	�O�&����0�����9��M�i�I��rq�H��,�Zx6]�����l�ݧSZ ߩ�V቎ֱ����lU6�K��@�3i/J�{�
����'Co[�ّ;�O?"2 ��]y��%�����a���>RJ�,0[�#�r�L�|8���k�=gs���]�߮��N!2[f�b���w�r0���o��&�&�&9+���%Wk���ǳ���l9I�k��a2��QBR�      =   [  x��MO#1D�����_m{�	p 	��J{q�Xcl�IXe�:{~���k���JIx�s�	���xrAh<���������ޏKC�&M�93@�5⾕���#�@���=�A��}�xc�9��n�&����5]`ؓ�h�^
�Ǹ.�ප�"O�ȳ���W뇆��|J��/���Y�X���M*�p�P���Vj���t��H���A=k�$��w��z�K�`��hr�{�����]�s�u�ƞ0<%��&�>����i{��*+����9bwL����Њ�;��'�,�r�yT�?pC����w�Z|)�d)��ړ�d�qB9S����k<�/�jf��F�;|�     