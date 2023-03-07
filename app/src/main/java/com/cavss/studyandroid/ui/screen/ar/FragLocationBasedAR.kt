package com.cavss.studyandroid.ui.screen.ar

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.cavss.studyandroid.R
import com.cavss.studyandroid.databinding.FragmentLocationBasedArBinding
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.SceneView
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable



// 37.572562, 127.012199
class FragLocationBasedAR : Fragment() {

    // 권한 요청 결과 처리를 위한 ActivityResultLauncher
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var binding : FragmentLocationBasedArBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try{
            binding = FragmentLocationBasedArBinding.inflate(inflater, container, false)

            // 권한 요청을 위한 ActivityResultLauncher를 초기화합니다.
            requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        // 권한이 승인되었을 때 처리할 작업 수행

                    } else {
                        // 권한이 거부되었을 때 처리할 작업 수행
                    }
                }

            ModelRenderable.builder()
                .setSource(
                    requireContext(),
                    Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.window)
                )
                .build()
                .thenAccept { renderable ->
                    modelRenderable = renderable
                    addModelToScene(binding.sceneView)
                }
                .exceptionally {e : Throwable ->
                    Log.e("mException", "FragLocationBasedAR, onCreateView, ModelRenderable.builder() // Exception : ${e.localizedMessage}")
                    null
                }
        }catch (e:Exception){
            Log.e("mException", "FragLocationBasedAR, onCreateView // Exception : ${e.localizedMessage}")
        }finally {
            return binding.root
        }
    }


    private lateinit var sceneView: SceneView
    private lateinit var modelRenderable: ModelRenderable
    private fun addModelToScene(setSceneView: SceneView) {
        try{
            this.sceneView = setSceneView

            val modelNode = Node()
            modelNode.renderable = modelRenderable
            modelNode.worldPosition = Vector3(37.572562f, 0f, 127.012199f)

            val camera = sceneView.scene.camera
            val cameraPos = Vector3(camera.worldPosition.x, camera.worldPosition.y, camera.worldPosition.z)
            val cameraDir = Vector3(camera.forward.x, camera.forward.y, camera.forward.z)

            val cameraDistance = 2f
            val modelPos = Vector3(
                cameraPos.x + cameraDir.x * cameraDistance,
                cameraPos.y + cameraDir.y * cameraDistance,
                cameraPos.z + cameraDir.z * cameraDistance
            )
            modelNode.worldPosition = modelPos

            sceneView.scene.addChild(modelNode)
        }catch (e:Exception){
            Log.e("mException", "FragLocationBasedAR, addModelToScene // Exception : ${e.localizedMessage}")
        }
    }

    private fun setSceneView(setSceneView: SceneView){
        try{
            this.sceneView = setSceneView

            val scene = Scene(sceneView)
            val modelUri = Uri.parse("sampledata/models/window.obj")
            val renderableFuture = ModelRenderable.builder()
                .setSource(requireContext(), modelUri)
                .build()

            renderableFuture.thenAccept { getRenderable ->
                val node = Node().apply {
                    setParent(scene)
                    renderable = getRenderable
                    localPosition = Vector3(0f, 0f, -1f)
                }
                scene.addChild(node)
            }
        }catch (e:Exception){
            Log.e("mException", "FragLocationBasedAR, setSceneView // Exception : ${e.localizedMessage}")
        }
    }

    override fun onDestroy() {
        try{
            super.onDestroy()
        }catch (e:Exception){
            Log.e("mException", "LocationBasedArFragment, onDestroy // Exception : ${e.localizedMessage}")
        }
    }
}