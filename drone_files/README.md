## Drone Simulation instructions 

### MAVSDK
1. copy the .deb file from the drone_files folder and install it using dpkg:
2. sudo dpkg -i mavsdk_0.37.0_ubuntu20.04_amd64.deb

### PX4 Simulator
1. run the following commands:
2. $ git clone https://github.com/PX4/Firmware.git --recursive
3. $ cd Firmware
4. $ bash ./Tools/setup/ubuntu.sh --no-nuttx

### QgroundControl
1. if it is the first time installing it run the following :
2. $ sudo usermod -a -G dialout $USER
3. $ sudo apt-get remove modemmanager -y
4. $ sudo apt install gstreamer1.0-plugins-bad gstreamer1.0-libav gstreamer1.0-gl -y

find the Qgroundcontrol image in the repo and install it using:
1. $ chmod +x ./QGroundControl.AppImage
2. double click QGroundControl.AppImage.

### Example files
1. git clone https://github.com/mavlink/MAVSDK.git --recursive
2. cd MAVSDK

Build Examples Instructions:
1. $ cd examples/$example_name/
2. $ mkdir build && cd build
3. $ cmake ..
4. $ make

### Run with the following command from the &example_name/build directory(with jmavsim running):
./&example_name udp://:14540
