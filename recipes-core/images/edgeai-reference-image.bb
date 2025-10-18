require tdx-reference-minimal-image.bb

SUMMARY = "Toradex Embedded Linux Reference Multimedia Image with TI edge ai"
DESCRIPTION = "Image for BSP verification with QT and multimedia features"

inherit populate_sdk_qt5

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "edgeai-multimedia-image"

IMAGE_FEATURES += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston', '', d)} \
"

#INITRAMFS_IMAGE = "initramfs-ostree-torizon-image"
#INITRAMFS_FSTYPES = "cpio.gz"

APP_LAUNCH_WAYLAND ?= "wayland-qtdemo-launch-cinematicexperience"



IMAGE_INSTALL += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         '${APP_LAUNCH_WAYLAND}', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', \
                         'weston-xwayland xterm', '', d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "tpm2", "packagegroup-tpm2-tdx-cli", "",d)} \
    \
    packagegroup-tdx-cli \
    packagegroup-tdx-graphical \
    packagegroup-tdx-qt5 \
    packagegroup-fsl-isp \
    \
    bash \
    coreutils \
    less \
    makedevs \
    mime-support \
    net-tools \
    util-linux \
    v4l-utils \
    \
    gpicview \
    media-files \
"

# packagegroup-arago-gst-sdk-target
IMAGE_INSTALL += " \
    gstreamer1.0-dev \
    gstreamer1.0-plugins-base-dev \
    gstreamer1.0-plugins-good-dev \
    gstreamer1.0-plugins-bad-dev \
    gstreamer1.0-libav-dev \
"

# packagegroup-edgeai-tisdk-addons.bb
IMAGE_INSTALL += " \
    cmake \
    boost \
    ninja \
    meson \
    opencv \
    opencv-dev \
    python3-pyyaml \
    python3-pycparser \
    python3-cffi \
    python3-numpy \
    python3-pybind11 \
    python3-wheel \
    python3-opencv \
    python3-pip \
    python3-pillow \
"

# EDGEAI_STACK

IMAGE_INSTALL += " \
        ti-vision-apps-dev \
        ti-tidl-dev \
        edgeai-tiovx-kernels-dev \
        edgeai-tiovx-kernels-source \
        edgeai-apps-utils-source \
        edgeai-test-data \
        edgeai-tidl-models \
        edgeai-tiovx-apps-dev \
        edgeai-tiovx-apps-source \
        ti-edgeai-firmware \
        ti-tidl-osrt-dev \
        ti-tidl-osrt-staticdev \
        edgeai-tiovx-modules-dev \
        edgeai-tiovx-modules-source \
        edgeai-gst-plugins-dev \
        edgeai-dl-inferer-staticdev \
        edgeai-gst-apps-source \
        edgeai-gst-plugins-source \
        edgeai-gst-apps-dev \
        edgeai-dl-inferer-source \
        ti-gpio-cpp-dev \
        ti-gpio-py \
        ti-gpio-cpp-source \
        ti-gpio-py-source \
        edgeai-studio-agent \
"
