
#include "stm32f10x.h"
#include "config.h"
#include "sys.h"
#include "usart.h"
#include "OLED.h"
#include "timer.c"
#include "tcs34725.c"

/******************************************************************************/
void com2_order(void);
/******************************************************************************/
void delay_s(u32 i)
{
	while(i--);
}
/******************************************************************************/
//主控stm32c8t6
//OLED 模块 SDA -- PB8
//		  SCL -- PB9
//TSC3472 模块
//          SDA -- PB11
//		  SCL -- PB10
//          VIN--5V
//USART1_TX   GPIOA.9
//USART1_RX	  GPIOA.10
void RCC_Configuration(void)
{
	RCC_DeInit();//RCC寄存器取消初始化设置
	RCC_HSEConfig(RCC_HSE_ON);//开启外部高速时钟
	RCC_WaitForHSEStartUp();//等待外部晶振启动
	FLASH_SetLatency(FLASH_Latency_2);//代码延时2周期
	FLASH_HalfCycleAccessCmd(FLASH_HalfCycleAccess_Disable);//半周期访问失能
	FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable);//预取指缓存使能
	RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);//外部高速时钟为PLL时钟输入源，9倍频
	RCC_PLLCmd(ENABLE);//使能PLL
	RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK);//PLL作为系统时钟
	RCC_HCLKConfig(RCC_SYSCLK_Div1);//AHB 时钟 = 系统时钟
	RCC_PCLK1Config(RCC_HCLK_Div2);//APB1 时钟 = HCLK / 2,即AHB时钟的一半
	RCC_PCLK2Config(RCC_HCLK_Div1);//APB2 时钟 = HCLK，即AHB时钟
	//RCC_ADCCLKConfig(RCC_PCLK2_Div6);//ADC 时钟 = PCLK / 6，即APB2时钟，72/6=12M
	RCC_LSEConfig(RCC_LSE_OFF);//关闭外部低速时钟
}
/******************************************************************************/
void GPIO_Config(void)
{
  GPIO_InitTypeDef GPIO_InitStructure;
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA|RCC_APB2Periph_GPIOB|RCC_APB2Periph_GPIOC|RCC_APB2Periph_AFIO, ENABLE);//使能GPIOA,GPIOB,GPIOC,AFIO;
	GPIO_PinRemapConfig(GPIO_Remap_SWJ_JTAGDisable,ENABLE);
	
	GPIO_InitStructure.GPIO_Pin = GPIO_Pin_3;        //是LED
	GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP; //复用推挽输出	
	GPIO_InitStructure.GPIO_Speed = GPIO_Speed_10MHz;//速度
	GPIO_Init(GPIOA, &GPIO_InitStructure);           //对选中管脚初始化
	GPIO_SetBits(GPIOA,GPIO_Pin_3);                  //上电点亮LED
}
/******************************************************************************/
/******************************************************************************/
int main(void)
{
	RCC_Configuration();
	GPIO_Config();
	uart_init(115200);
	OLED_Init();
	delay_s(0x1fffff); 
	TCS34725_Init();
	printf("Initial OK!\r\n");
	TIM2_Init(999,71);  //1ms定时

	while(1)
	{
		if(time1_cntr>=200)  //0.2s
			{
				time1_cntr=0;
				LED_blink;
			}
		if(time2_cntr>=1000)  //1s
			{
				time2_cntr=0;
				TCS34725_GetRawData(&rgb);  //读两次，实际测试时发现读到的颜色总是上一次的颜色
				// 定义颜色枚举类型
				
				//颜色判断，根据串口输出实际情况调整数值
			if (rgb.r> 143 && rgb.g < 80 && rgb.b< 80)
				{
					OLED_Clear();	
					 OLED_ShowString(4, 4, "RED");
				} 
			else if (rgb.r >60&& rgb.g >28 && rgb.b< 40) 
				{
					OLED_Clear();	
					 OLED_ShowString(4, 4, "YELLOW");
				} 
				else if (rgb.r< 150 && rgb.g < 290 && rgb.b > 208) 

				{
					OLED_Clear();	
					 OLED_ShowString(4, 4, "BLUE");
				} else if (rgb.r < 90 && rgb.g > 140 && rgb.b < 148) 
				{
					 OLED_Clear();	
					 OLED_ShowString(4, 4, "GREEN");
					
				} else 
				{
					OLED_Clear();	
					 OLED_ShowString(3, 2, "waiting");
					
				}
			}

				RGBtoHSL(&rgb,&hsl);
				printf("R=%d G=%d B=%d \r\n",rgb.r,rgb.g,rgb.b);
			//	printf("H=%d S=%d L=%d\r\n",hsl.h,hsl.s,hsl.l);//转化为hsl色域可用可不用
			
			}
	}
/******************************************************************************/
//{"color":{"h":240,"s":50,"l":50}}  //[0-359][0-100][0-100]

