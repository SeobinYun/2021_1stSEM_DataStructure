public class MergeSorting {
        public static void main(String[] args) {
        int[] array = new int[32];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 32);
            for (int j = 0; j < i; j++) { // 중복값 확인: 배열 index j부터, j가 i전일 때까지 비교 반복
                if (array[j] == array[i]) { // index j방의 값과 i방의 값이 값다면
                    i--; // index i 위치의 값을 다시 할당받아야 하기 때문에 i--를 해주고 비교반복문 탈출
                    break;
                }
            }
        }
        System.out.print("배열생성 : "); // 랜덤으로 생성한 배열 출력
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }


        mergeSort(array, 0, array.length); // mergeSort method 실행

        System.out.print("\n최종배열출력 : "); 
        for (int i = 0; i < array.length; i++) { // insertionSorting method 실행 후 최종배열 출력
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }


    private static void merge(int[] data, int first, int n1, int n2){ // divide 후 배열을 합치는 merge method
            int[] tmp= new int[n1+n2];
            int copied=0; // data 배열에서 tmp로 얼만큼 복사했는지 저장할 변수
            int copied1= 0; // data 배열의 왼쪽 부분에서 얼만큼 복사했는지 저장할 변수
            int copied2=0; // data 배열의 오른쪽 부분에서 얼만큼 복사했는지 저장할 변수

            while((copied1<n1)&&(copied2<n2)){ // 우선 copied1, copied2가 각각 n1, n2보다 작아야함
                if(data[first+copied1]<data[first+n1+copied2]){ // 왼쪽에 있는게 오른쪽에 있는 것보다 작으면 tmp에 왼쪽 값을 넣어줌 
                    tmp[copied++]=data[first+(copied1++)];
                }
                else{ // 아니면 오른쪽 값이 작다는 뜻이니 오른쪽 값을 tmp에 넣어줌
                    tmp[copied++]=data[first+n1+(copied2++)];
                }
            }

            while(copied1<n1){ // data 배열의 왼쪽 부분 중, 남은 거 copy
                tmp[copied++]=data[first+(copied1++)];
            }

            while(copied2<n2){ // data 배열의 오른쪽 부분 중, 남은 거 copy
                tmp[copied++]=data[first+n1+(copied2++)];
            }

            for(int i=0; i< copied; i++){ // data에 이때까지 구했던 tmp 배열을 복사해줌
                data[first+i]=tmp[i];
            }

        System.out.print("\nmerge 실행 : ");
        for(int i=0; i<data.length; i++){ // selectionSorting method 실행 후 최종배열 출력
            System.out.print(data[i] + "\t");
        }
    }

    private static void mergeSort(int data[], int first, int n){ // mergeSort method
            if(n>1){ // 우선 배열의 길이가 1 이상일 때 mergeSort method 실행
                int n1=n/2; // 배열의 중간값을 나타내는 n1 변수
                int n2=n-n1; // 총 길이 n에서 n1만큼을 뺀 n2 변수

                mergeSort(data, first, n1); // 배열의 왼쪽 부분을 또 mergeSort 해줌
                mergeSort(data, first+n1, n2); // 배열의 오른쪽 부분을 또 mergeSort 해줌
                merge(data, first, n1, n2); // 그 후 merge method를 통해 합쳐줌
            }
    }
}
