
REPO=${HOME}/.m2/repository

MAVEN=${REPO}/com/google/gwt/gwt-dev/2.8.1/gwt-dev-2.8.1.jar:\
${REPO}/com/google/gwt/gwt-user/2.8.1/gwt-user-2.8.1.jar:\
${REPO}/ant/ant/1.6.5/ant-1.6.5.jar:\
${REPO}/colt/colt/1.2.0/colt-1.2.0.jar:\
${REPO}/org/ow2/asm/asm/5.0.3/asm-5.0.3.jar:\
${REPO}/org/ow2/asm/asm-util/5.0.3/asm-util-5.0.3.jar:\
${REPO}/org/ow2/asm/asm-tree/5.0.3/asm-tree-5.0.3.jar:\
${REPO}/org/ow2/asm/asm-commons/5.0.3/asm-commons-5.0.3.jar:\
${REPO}/tapestry/tapestry/4.0.2/tapestry-4.0.2.jar:\
${REPO}/commons-io/commons-io/2.4/commons-io-2.4.jar

ORIG=${HOME}/Projects/appinventor-sources/appinventor/lib/gwt/2.8.1-patched/gwt-dev.jar:\
${HOME}/Projects/appinventor-sources/appinventor/lib/gwt/2.8.1-patched/gwt-user.jar

#GWT_QUERY=${REPO}/com/googlecode/gwtquery/gwtquery/1.5-beta1/gwtquery-1.5-beta1.jar
GWT_QUERY=${HOME}/Projects/appinventor-sources/appinventor/lib/gwt_query/gwtquery-1.5-beta1.jar

CP=src/main/java:\
target/classes:\
target/generated-sources/java-templates:\
target/generated-sources/descriptors:\
${MAVEN}:\
${GWT_QUERY}:\
${REPO}/com/google/guava/guava/20.0/guava-20.0.jar:\
${REPO}/com/google/guava/guava-gwt/20.0/guava-gwt-20.0.jar:\
${REPO}/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar:\
${REPO}/com/google/errorprone/error_prone_annotations/2.0.12/error_prone_annotations-2.0.12.jar:\
${REPO}/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar:\
${REPO}/com/google/jsinterop/jsinterop-annotations/1.0.1/jsinterop-annotations-1.0.1.jar:\
${REPO}/com/google/jsinterop/jsinterop-annotations/1.0.1/jsinterop-annotations-1.0.1-sources.jar:\
${REPO}/javax/validation/validation-api/1.0.0.GA/validation-api-1.0.0.GA.jar:\
${REPO}/javax/validation/validation-api/1.0.0.GA/validation-api-1.0.0.GA-sources.jar:\
${REPO}/com/google/gwt/gwt-incubator/2.0.1/gwt-incubator-2.0.1.jar:\
${REPO}/com/allen-sauer/gwt/dnd/gwt-dnd/3.3.4/gwt-dnd-3.3.4.jar:\
${REPO}/com/appinventor/ai-component-common/1.0/ai-component-common-1.0.jar:\
${REPO}/com/appinventor/ai-common/1.0/ai-common-1.0.jar


#md5sum ${REPO}/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar #OK
#md5sum ${REPO}/com/google/guava/guava/20.0/guava-20.0.jar # OK
#md5sum ${REPO}/com/google/guava/guava-gwt/20.0/guava-gwt-20.0.jar # OK
#md5sum ${REPO}/com/google/errorprone/error_prone_annotations/2.0.12/error_prone_annotations-2.0.12.jar # OK
#md5sum ${REPO}/com/google/j2objc/j2objc-annotations/1.1/j2objc-annotations-1.1.jar # OK
#md5sum ${REPO}/com/allen-sauer/gwt/dnd/gwt-dnd/3.2.3/gwt-dnd-3.2.3.jar # OK

#md5sum ${REPO}/com/google/gwt/gwt-incubator/2.0.1/gwt-incubator-2.0.1.jar
#md5sum ${HOME}/Projects/appinventor-sources/appinventor/lib/gwt_incubator/gwt-incubator-20101117-r1766.jar

#md5sum ${REPO}/com/googlecode/gwtquery/gwtquery/1.5-beta1/gwtquery-1.5-beta1.jar
#md5sum ${HOME}/Projects/appinventor-sources/appinventor/lib/gwt_query/gwtquery-1.5-beta1.jar

#exit 1

java -cp ${CP} com.google.gwt.dev.Compiler \
    -war target/ai-front-1.0/ \
    -logLevel INFO \
    -style pretty \
    com.google.appinventor.YaClient
